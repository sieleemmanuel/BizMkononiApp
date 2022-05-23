package com.siele.mkononiapptest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentCustomersInsightsBinding
import com.siele.mkononiapptest.interfaces.DrawerClosed
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker

class CustomersInsights : Fragment() {
    private lateinit var binding: FragmentCustomersInsightsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomersInsightsBinding.inflate(inflater)
        (activity as DrawerAndBarsLocker?)?.setDrawerLocked(false)
        (activity as DrawerAndBarsLocker?)?.setBarsLocked(false)
        (activity as DrawerClosed).closeDrawer(true)

        binding.apply {
            /*customersInsightsToolbar.setNavigationOnClickListener {
                (activity as DrawerClosed).openDrawer(true)
            }*/
            searchView.queryHint = getString(R.string.searchview_hint)
        }
        return binding.root
    }
}