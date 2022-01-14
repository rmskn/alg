package com.example.shop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.ItemCartProductBinding
import com.example.shop.databinding.ItemProfileBinding
import com.example.shop.model.Product
import com.example.shop.model.ProfileItem

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private val items = mutableListOf<ProfileItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setProfileItems(profileItems: List<ProfileItem>) {
        items.clear()
        items.addAll(profileItems)
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(profileItem: ProfileItem) {
            val binding = ItemProfileBinding.bind(itemView)
            binding.textViewTitle.text = profileItem.itemName
        }
    }
}