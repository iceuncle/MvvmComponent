package com.example.module_main.ui

import Constant
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module_base.base.BaseActivity
import com.example.module_base.utils.RouteCenter
import com.example.module_main.R
import com.example.module_main.databinding.MainActivityBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {

    override val layoutId: Int get() = R.layout.main_activity

    override fun initView() {
        mBinding.toolbar.run {
            setSupportActionBar(this)
        }

        mBinding.drawerLayout.run {
            val toggle = ActionBarDrawerToggle(this@MainActivity,
                    this,
                    mBinding.toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }

        mBinding.navView.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }

//        setupBottomNavigationBar()
        initViewPager()
    }

    private fun initViewPager() {
        mBinding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int) = when (position) {
                0 -> RouteCenter.navigate(Constant.ROUTE_HOME) as Fragment
                1 -> RouteCenter.navigate(Constant.ROUTE_PROJECT) as Fragment
                else -> RouteCenter.navigate(Constant.ROUTE_DATA) as Fragment
            }
        }
        mBinding.viewPager.isUserInputEnabled = false
        val ids = listOf(R.id.home, R.id.project, R.id.mine)
        mBinding.bottomNav.setOnNavigationItemSelectedListener {
            it.isChecked = true
            mBinding.viewPager.currentItem = ids.indexOf(it.itemId)
            false
        }
    }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_like -> RouteCenter.navigate(Constant.ROUTE_LOGIN)
                    R.id.nav_about -> RouteCenter.navigate(Constant.ROUTE_LOGIN)
                }
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            }

//    private fun setupBottomNavigationBar() {
//        val navGraphIds = listOf(R.navigation.home, R.navigation.project, R.navigation.mine)
//        val controller = mBinding.bottomNav.setupWithNavController(
//                navGraphIds = navGraphIds,
//                fragmentManager = supportFragmentManager,
//                containerId = R.id.nav_host_container,
//                intent = intent
//        )
//        controller.observe(this, { navController ->
//            setupActionBarWithNavController(navController)
//        })
//    }

}