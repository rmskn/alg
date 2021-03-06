package com.example.shop.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shop.R
import com.example.shop.databinding.ItemCartProductBinding
import com.example.shop.domain.model.Product

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val items = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setProducts(products: List<Product>) {
        items.clear()
        items.addAll(products)
        notifyDataSetChanged()
    }

    fun clearCart() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            val binding = ItemCartProductBinding.bind(itemView)
            with(binding) {
                textViewPrice.text = product.price
                textViewProductName.text = product.title
                Glide
                    .with(itemView.context)
                    .load(product.photoUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        }
    }
}
