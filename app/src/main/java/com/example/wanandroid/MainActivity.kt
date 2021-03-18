package com.example.wanandroid

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.MainActivityBinding
import com.example.wanandroid.ui.demo.DataFragment
import com.example.wanandroid.ui.demo.DemoFragment

class MainActivity : BaseActivity<MainActivityBinding>() {


    override val layoutId: Int get() = R.layout.main_activity

    override fun initView() {

        mBinding.viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int = 3
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    2 -> DataFragment.newInstance()
                    else -> DemoFragment.newInstance(position)
                }
            }
        }

        val bottomNavIds = arrayListOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
        mBinding.navView.setOnNavigationItemSelectedListener { item ->
            mBinding.viewPager.currentItem = bottomNavIds.indexOf(item.itemId)
            true
        }
        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mBinding.navView.selectedItemId = bottomNavIds[position]
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })


    }
}