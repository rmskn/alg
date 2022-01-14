package com.example.shop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.activity.MainActivity
import com.example.shop.adapter.ProfileAdapter
import com.example.shop.data.DataSource
import com.example.shop.databinding.FragmentProfileBinding

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val adapter = ProfileAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = true)

        with(binding) {
            recyclerViewProfile.adapter = adapter
            adapter.setProfileItems(DataSource.profileItems)
        }
    }
}