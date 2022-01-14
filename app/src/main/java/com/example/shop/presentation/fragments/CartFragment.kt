package com.example.shop.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.shop.R
import com.example.shop.presentation.activity.MainActivity
import com.example.shop.presentation.adapter.CartAdapter
import com.example.shop.databinding.FragmentCartBinding
import com.example.shop.domain.model.Product
import com.example.shop.presentation.viewmodel.CartViewModel
import kotlinx.coroutines.flow.*

class CartFragment: Fragment(R.layout.fragment_cart) {

    companion object {
        fun newInstance() = CartFragment()
    }

    private val cartViewModel by lazy { CartViewModel(requireContext(), lifecycleScope) }

    private lateinit var binding: FragmentCartBinding

    private val adapter = CartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        binding.recyclerViewCart.adapter = adapter

        if (savedInstanceState == null) {
            cartViewModel.loadData()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            cartViewModel.loadData()
        }

        binding.textViewUpdate.setOnClickListener {
            cartViewModel.loadData()
        }

        cartViewModel.screenState.onEach {
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