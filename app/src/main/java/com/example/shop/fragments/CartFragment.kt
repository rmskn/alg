package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CartAdapter
import com.example.shop.data.DataSource
import com.example.shop.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {

    companion object {
        fun newInstance() = CartFragment()
    }

    private val adapter = CartAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCartBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewCart.adapter = adapter
            adapter.setProducts(DataSource.cartProducts)
        }
    }
}