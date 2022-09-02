package com.siele.mkononiapptest.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siele.mkononiapptest.MainActivity
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentSalesInsightsBinding
import com.siele.mkononiapptest.interfaces.DrawerClosed
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker
import org.eazegraph.lib.models.PieModel


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

        (activity as MainActivity?)!!.apply {
            handleSideNavClicks()
            updateBottomNav()
            handleBottomNavActions()
        }
        binding.apply {
            pieChart.apply {
                addPieSlice(PieModel("Roses",55F,resources.getColor(R.color.roses_color, null)))
                addPieSlice(PieModel("Daffoiles",20F,resources.getColor(R.color.daffoiles_color, null)))
                addPieSlice(PieModel("Lilies",29F,resources.getColor(R.color.lilies_color, null)))
                addPieSlice(PieModel("Daises",9F,resources.getColor(R.color.daises_color, null)))
                startAnimation()
                innerValueString = "55%"
            }

        }
        return binding.root
    }
}