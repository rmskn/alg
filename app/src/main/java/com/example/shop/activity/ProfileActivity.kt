package com.example.shop.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.R
import com.example.shop.adapter.ProfileAdapter
import com.example.shop.data.DataSource
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity: AppCompatActivity() {
    private val adapter = ProfileAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        recyclerViewProfile.adapter = adapter
        adapter.setProfileItems(DataSource.profileItems)
    }
}