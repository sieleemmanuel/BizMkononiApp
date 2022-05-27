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
import com.siele.mkononiapptest.model.GroupHeader


class MainActivity : AppCompatActivity(), DrawerAndBarsLocker, DrawerClosed {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private var titlesList = mutableListOf<GroupHeader>()

    private val menuItemList = mutableMapOf<GroupHeader, List<String>>()

    private val insights = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )
    private val businesses = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )
    private val sales = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )
    private val suppliers = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )
    private val finances = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )
    private val customers = arrayListOf(
        "Overview",
        "Sale Insights",
        "Customer Insights",
        "Chum Rate Insights",
        "Revenue Insights"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.customersInsights, R.id.salesInsights), drawerLayout)
        binding.apply {
            mainToolbar.apply {
                setupWithNavController(navController, appBarConfiguration)
            }

            sideNav.setupWithNavController(navController)
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
        titlesList.addAll(
            listOf(
                GroupHeader(R.drawable.ic_insights, "Insights"),
                GroupHeader(0, "SECTION"),
                GroupHeader(R.drawable.ic_businesses, "Businesses"),
                GroupHeader(R.drawable.ic_cart, "Sales"),
                GroupHeader(R.drawable.ic_supply, "Suppliers"),
                GroupHeader(R.drawable.ic_money, "Finances"),
                GroupHeader(R.drawable.ic_customers, "Customers")
            )
        )

        Log.d(TAG, "TitleList: $titlesList")
        menuItemList[titlesList[0]] = insights
        menuItemList[titlesList[1]] = listOf()
        menuItemList[titlesList[2]] = businesses
        menuItemList[titlesList[3]] = sales
        menuItemList[titlesList[4]] = suppliers
        menuItemList[titlesList[5]] = finances
        menuItemList[titlesList[6]] = customers
        menuItemList.toMap()
        titlesList.toList()

        Log.d(TAG, "TitleListItems: $menuItemList")
        val expandableListView = binding.expandableListView
        expandableListAdapter = ExpandableListAdapter(
            titlesList,
            menuItemList,
            this
        )
        expandableListView.setAdapter(expandableListAdapter)

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
                bottomNav.visibility = View.GONE
            } else {
                mainToolbar.visibility = View.VISIBLE
                bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun closeDrawer(shouldClose: Boolean) {
        val btnCloseDrawer =
            binding.sideNav.getHeaderView(0).findViewById<ImageButton>(R.id.ibCloseDrawer)
        btnCloseDrawer.setOnClickListener {
            if (shouldClose) {
                drawerLayout.close()
            }
        }
    }

    override fun openDrawer(shouldOpen: Boolean) {
        if (shouldOpen) {
            drawerLayout.open()
        }
    }

    fun handleSideNavClicks() {
        binding.expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            if (groupPosition == 0) {
                when (childPosition) {
                    1 -> {
                        drawerLayout.close()
                        navController.navigate(R.id.salesInsights)

                    }
                    2 -> {
                        drawerLayout.close()
                        navController.navigate(R.id.customersInsights)

                    }
                }
            }
            false
        }
    }

    fun updateBottomNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.customersInsights -> {
                    binding.bottomNav.setItemSelected(R.id.actionCustomerISights)
                }
                R.id.salesInsights -> {
                    binding.bottomNav.setItemSelected(R.id.actionBusinesses)
                }
            }
        }
    }

    fun handleBottomNavActions() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.setOnItemSelectedListener { itemId ->
                when (itemId) {
                    R.id.actionBusinesses -> {
                        if (destination.id == R.id.customersInsights) {
                            navController.navigate(R.id.action_customersInsights_to_salesInsights)
                        }
                    }
                    R.id.actionCustomerISights -> {
                        if (destination.id == R.id.salesInsights) {
                            navController.navigate(R.id.action_salesInsights_to_customersInsights)
                        }
                    }
                    R.id.actionAccount -> {
                        Toast.makeText(this, "No Account yet", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}