package com.siele.mkononiapptest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siele.mkononiapptest.databinding.FragmentSalesInsightsBinding
import com.siele.mkononiapptest.interfaces.DrawerClosed
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker


class SalesInsights : Fragment() {
    private lateinit var binding: FragmentSalesInsightsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesInsightsBinding.inflate(inflater)
        (activity as DrawerAndBarsLocker?)?.setDrawerLocked(false)
        (activity as DrawerAndBarsLocker?)?.setBarsLocked(false)
        (activity as DrawerClosed).closeDrawer(true)

        /*binding.salesInsightsToolbar.setNavigationOnClickListener {
            (activity as DrawerClosed).openDrawer(true)
        }
        binding.bottomNav.setOnItemSelectedListener {
            when(it){
               R.id.actionCustomerISights ->{
                   findNavController().navigate(R.id.action_signIn_to_customersInsights)
               }
            }
        }*/
        return binding.root
    }
}