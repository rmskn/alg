package com.example.shop.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop.R
import com.example.shop.adapter.CartAdapter
import com.example.shop.adapter.CatalogAdapter
import com.example.shop.data.DataSource
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity: AppCompatActivity() {

    private val adapter = CartAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        recyclerViewCart.adapter = adapter
        adapter.setProducts(DataSource.cartProducts)
    }
}