package com.siele.mkononiapptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siele.mkononiapptest.MainActivity
import com.siele.mkononiapptest.databinding.FragmentCustomersInsightsBinding
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker
import com.siele.mkononiapptest.interfaces.DrawerClosed

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

        (activity as MainActivity?)!!.apply {
            handleSideNavClicks()
            updateBottomNav()
            handleBottomNavActions()
        }

        return binding.root
    }
}