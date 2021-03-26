package com.example.wanandroid.ui.main

import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.MainActivityBinding
import com.example.wanandroid.utils.setupWithNavController
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
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.home, R.navigation.project, R.navigation.mine)
        val controller = mBinding.bottomNav.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                intent = intent
        )
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
    }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_like -> {

                    }
                    R.id.nav_about -> {

                    }
                }
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
}