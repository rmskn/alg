package com.example.shop.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shop.R
import com.example.shop.presentation.activity.MainActivity
import com.example.shop.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        (activity as? MainActivity)?.changeMenuState(isVisible = false)

        binding.buttonLogin.setOnClickListener {
            (activity as? MainActivity)?.navigateToFragment(CatalogFragment.newInstance())
        }
    }
}