package com.example.shop.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.databinding.ActivityMainBinding
import com.example.shop.fragments.CartFragment
import com.example.shop.fragments.CatalogFragment
import com.example.shop.fragments.LoginFragment
import com.example.shop.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        if (savedInstanceState == null) {
            navigateToFragment(LoginFragment.newInstance())
        }

        binding.bottomNavigationView.menu.findItem(R.id.itemCart).setOnMenuItemClickListener {
            navigateToFragment(CartFragment.newInstance())
            it.isChecked = true
            true
        }

        binding.bottomNavigationView.menu.findItem(R.id.itemCatalog).setOnMenuItemClickListener {
            navigateToFragment(CatalogFragment.newInstance())
            it.isChecked = true
            true
        }

        binding.bottomNavigationView.menu.findItem(R.id.itemProfile).setOnMenuItemClickListener {
            navigateToFragment(ProfileFragment.newInstance())
            it.isChecked = true
            true
        }
    }

    fun navigateToFragment(fmt: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fmt)
            .addToBackStack(fmt.javaClass.name)
            .commit()
    }

    fun changeMenuState(isVisible: Boolean) {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).isVisible = isVisible
    }
}