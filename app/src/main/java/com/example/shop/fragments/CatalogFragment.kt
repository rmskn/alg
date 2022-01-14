package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CatalogAdapter
import com.example.shop.data.DataSource
import com.example.shop.databinding.FragmentCatalogBinding
import com.example.shop.ext.onClickFlow
import com.example.shop.ext.onRefreshFlow
import com.example.shop.model.Product
import com.example.shop.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private lateinit var binding: FragmentCatalogBinding

    private val adapter = CatalogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewProducts.adapter = adapter

        }

        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.textViewUpdate.onClickFlow()
        ).flatMapLatest {
            getProducts()
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

    private fun getProducts() = flow {
        emit(ScreenState.Loading)
        val catalogProducts = NetworkService.loadCatalog()
        emit(ScreenState.DataLoaded(catalogProducts))
    }.catch {
        emit(ScreenState.Error(getString(R.string.just_error)))
    }

    private fun setStateLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !recyclerViewProducts.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && recyclerViewProducts.isVisible
    }

    private fun setStateData(products: List<Product>?) = with(binding) {
        recyclerViewProducts.isVisible = products != null
        adapter.setProducts(products ?: emptyList())
    }

    private fun setStateError(error: String?) = with(binding) {
        constraintLayoutError.isVisible = error != null
        textViewError.text = error
    }
}

sealed class ScreenState {
    data class DataLoaded(val products: List<Product>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}
