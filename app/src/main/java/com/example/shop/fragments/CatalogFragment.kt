package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.CatalogAdapter
import com.example.shop.data.DataSource
import com.example.shop.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private val adapter = CatalogAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCatalogBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewProducts.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewProducts.adapter = adapter
            adapter.setProducts(DataSource.catalogProducts)
        }
    }
}
