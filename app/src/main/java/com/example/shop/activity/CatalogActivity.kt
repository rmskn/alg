package com.example.shop.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop.R
import com.example.shop.adapter.CatalogAdapter
import com.example.shop.data.DataSource
import kotlinx.android.synthetic.main.activity_catalog.*

class CatalogActivity : AppCompatActivity() {

    private val adapter = CatalogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.adapter = adapter
        adapter.setProducts(DataSource.catalogProducts)
    }
}
