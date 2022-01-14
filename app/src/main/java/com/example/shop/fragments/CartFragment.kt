package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CartAdapter
import com.example.shop.databinding.FragmentCartBinding
import com.example.shop.ext.onClickFlow
import com.example.shop.ext.onRefreshFlow
import com.example.shop.model.Product
import com.example.shop.network.NetworkService
import kotlinx.coroutines.flow.*

class CartFragment: Fragment(R.layout.fragment_cart) {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var binding: FragmentCartBinding

    private val adapter = CartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        binding.recyclerViewCart.adapter = adapter

        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.textViewUpdate.onClickFlow()
        ).flatMapLatest {
            getCart()
        }
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    is ScreenState.DataLoaded -> {
                        setStateLoading(false)
                        setStateError(null)
                        setStateData(it.products)
                    }
                    is ScreenState.Error -> {
                        setStateLoading(false)
                        setStateError(it.error)
                        setStateData(null)
                    }
                    ScreenState.Loading -> {
                        setStateLoading(true)
                        setStateError(null)
                        setStateData(null)
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun getCart() = flow {
        emit(ScreenState.Loading)
        val catalogProducts = NetworkService.loadCart()
        emit(ScreenState.DataLoaded(catalogProducts))
    }.catch {
        emit(ScreenState.Error(getString(R.string.just_error)))
    }

    private fun setStateLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !recyclerViewCart.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && recyclerViewCart.isVisible
    }

    private fun setStateData(products: List<Product>?) = with(binding) {
        recyclerViewCart.isVisible = products != null
        adapter.setProducts(products ?: emptyList())
    }

    private fun setStateError(error: String?) = with(binding) {
        constraintLayoutError.isVisible = error != null
        textViewError.text = error
    }

}