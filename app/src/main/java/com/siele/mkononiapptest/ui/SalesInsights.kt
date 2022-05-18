package com.siele.mkononiapptest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentSalesInsightsBinding


class SalesInsights : Fragment() {
    private lateinit var binding: FragmentSalesInsightsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesInsightsBinding.inflate(inflater)
        return binding.root
    }
}