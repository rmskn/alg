package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CatalogAdapter
import com.example.shop.data.DataSource
import com.example.shop.databinding.FragmentCatalogBinding
import com.example.shop.network.NetworkService
import kotlinx.coroutines.*

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private lateinit var binding: FragmentCatalogBinding

    private val scope = CoroutineScope(Dispatchers.Main + Job() + CoroutineExceptionHandler { _, exception ->
        showError(exception)
    })

    private val adapter = CatalogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewProducts.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                getProducts()
            }
        }

        getProducts()
    }

    private fun showError(error: Throwable) {
        Toast.makeText(context, getString(R.string.error, error.toString()), Toast.LENGTH_LONG).show()
        binding.progressBar.isGone = true
    }

    private fun getProducts() {
        scope.launch {
            adapter.clearProducts()
            binding.progressBar.isVisible = true
            val catalogProducts = NetworkService.loadCatalog()
            adapter.setProducts(catalogProducts)
            binding.progressBar.isGone = true
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
