package com.example.shop.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CartAdapter
import com.example.shop.databinding.FragmentCartBinding
import com.example.shop.network.NetworkService
import kotlinx.coroutines.*

class CartFragment: Fragment(R.layout.fragment_cart) {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var binding: FragmentCartBinding

    private val scope = CoroutineScope(Dispatchers.Main + Job() + CoroutineExceptionHandler { _, exception ->
        showError(exception)
    })

    private val adapter = CartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewCart.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                getCart()
            }
        }

        getCart()
    }

    private fun showError(error: Throwable) {
        Toast.makeText(context, getString(R.string.error, error.toString()), Toast.LENGTH_LONG).show()
        binding.progressBar.isGone = true
    }

    private fun getCart() {
        scope.launch {
            adapter.clearCart()
            binding.progressBar.isVisible = true
            val cartProducts = NetworkService.loadCart()
            adapter.setProducts(cartProducts)
            binding.progressBar.isGone = true
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}