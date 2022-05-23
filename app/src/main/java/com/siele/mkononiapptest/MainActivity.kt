package com.siele.mkononiapptest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.siele.mkononiapptest.adapters.ExpandableListAdapter
import com.siele.mkononiapptest.databinding.ActivityMainBinding
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker
import com.siele.mkononiapptest.interfaces.DrawerClosed


class MainActivity : AppCompatActivity(), DrawerAndBarsLocker, DrawerClosed {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navController:NavController
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var titlesList:List<String>

    private val menuItemList =  mutableMapOf<String, List<String>>()

    private val insights = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")
    val businesses = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")
    val sales = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")

    private val suppliers = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")

    private val finances = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")

    private val customers = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.customersInsights,R.id.salesInsights), drawerLayout)
        binding.apply {
            mainToolbar.apply {
                setupWithNavController(navController, appBarConfiguration)
            }

            sideNav.setupWithNavController(navController)
            bottomNav.setOnItemSelectedListener {
                when(it){
                    R.id.actionBusinesses ->{

                    }
                }

            }
        }
        setupExpandableMenu()


    }



    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    private fun setupExpandableMenu() {
        menuItemList["Insights"] = insights
        menuItemList["SECTION"] = listOf()
        menuItemList["Businesses"] = businesses
        menuItemList["Sales"] = sales
        menuItemList["Suppliers"] = suppliers
        menuItemList["Finances"] = finances
        menuItemList["Customers"] = customers

        Log.d("list", ":: $menuItemList")
        val expandableListView =  binding.expandableListView

        titlesList = ArrayList(menuItemList.keys)
        expandableListAdapter = ExpandableListAdapter(
            titlesList,
            menuItemList,
            this)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.apply {
            setOnGroupClickListener { parent, v, groupPosition, id ->
                groupPosition == 1
            }
            setOnGroupExpandListener { groupPosition ->

                Toast.makeText(
                    this.context,
                    "${ titlesList [groupPosition] } Expanded",
                    Toast.LENGTH_LONG).show()
            }
            setOnGroupCollapseListener {
                Toast.makeText(
                    this.context,
                    "${ (titlesList as ArrayList<String>)[it] } Collapsed",
                    Toast.LENGTH_LONG).show()

            }
            setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(
                    this.context,
                    "Clicked: ${titlesList[groupPosition]} => ${menuItemList[titlesList[groupPosition]]!![childPosition]}",
                    Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    override fun setBarsLocked(lockBars: Boolean) {
        binding.apply {
            if (lockBars) {
                mainToolbar.visibility = View.GONE
                bottomNav.visibility  = View.GONE
            }else{
                mainToolbar.visibility = View.VISIBLE
                bottomNav.visibility  = View.VISIBLE
            }
        }
    }

    override fun closeDrawer(shouldClose: Boolean) {
        val btnCloseDrawer = binding.sideNav.getHeaderView(0).findViewById<ImageButton>(R.id.ibCloseDrawer)
        btnCloseDrawer.setOnClickListener {
            if (shouldClose) {
                drawerLayout.close()
            }
        }
    }

    override fun openDrawer(shouldOpen: Boolean) {
        if (shouldOpen){
            drawerLayout.open()
        }
    }
}