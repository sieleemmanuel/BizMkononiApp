package com.siele.mkononiapptest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentCustomersInsightsBinding

class CustomersInsights : Fragment() {
    private lateinit var binding: FragmentCustomersInsightsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomersInsightsBinding.inflate(inflater)
        binding.searchView.queryHint = getString(R.string.searchview_hint)

        return binding.root
    }
}