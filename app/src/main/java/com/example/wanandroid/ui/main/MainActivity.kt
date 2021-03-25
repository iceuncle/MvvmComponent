package com.example.wanandroid.ui.main

import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.MainActivityBinding
import com.example.wanandroid.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {


    override val layoutId: Int get() = R.layout.main_activity

    override fun initView() {
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
}