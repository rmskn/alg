package com.example.shop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.ItemProductBinding
import com.example.shop.model.Product

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private val items = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
       return CatalogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<Product>) {
        items.clear()
        items.addAll(products)
        notifyDataSetChanged()
    }

    inner class CatalogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            val binding = ItemProductBinding.bind(itemView)
            with(binding) {
                textViewPrice.text = product.price
                textViewProductName.text = product.title
            }
        }
    }
}

