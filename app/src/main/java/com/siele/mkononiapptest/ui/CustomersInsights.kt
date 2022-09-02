package com.siele.mkononiapptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.siele.mkononiapptest.MainActivity
import com.siele.mkononiapptest.R
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
        binding.horizontalChart.isHorizontalScrollBarEnabled = true

        setData()



        return binding.root
    }

    private fun setData() {
        val entryValues = arrayListOf<BarEntry>()

        entryValues.add(BarEntry(0f, 18f))
        entryValues.add(BarEntry(1f, 24f))
        entryValues.add(BarEntry(2f, 35f))
        entryValues.add(BarEntry(3f, 20f))
        entryValues.add(BarEntry(4f, 45f))
        entryValues.add(BarEntry(5f, 29F))
        entryValues.add(BarEntry(6f, 15F))

        val days = arrayListOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")

        val dataSet = BarDataSet(entryValues, "")
        dataSet.color = resources.getColor(R.color.dark_blue, null)
        val barData = BarData(dataSet)
        val formatter = object :ValueFormatter(){
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return days.getOrNull(value.toInt())?:value.toString()
            }
        }
        barData.barWidth = 0.5f

        binding.horizontalChart.apply {
            description = null
            legend.isEnabled = false
            data = barData
            animateY(2000)
            invalidate()
            xAxis.apply {
                setDrawGridLines(false)
                position = XAxis.XAxisPosition.BOTTOM
                isEnabled = true
                valueFormatter = formatter
                labelCount = 7
                setDrawAxisLine(false)
                spaceMin = 0.5f
            }
            axisLeft.apply {
                setDrawGridLines(false)
                setDrawGridLines(false)
                setDrawZeroLine(false)
                isEnabled = false


            }
            axisRight.apply {
                axisMaximum = 50F
                axisMinimum = 0f
                setDrawGridLines(false)
                setDrawGridLines(false)
                setDrawZeroLine(true)
                setDrawAxisLine(false)
                setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            }
            axisRight.minWidth = 0F
        }

    }
}