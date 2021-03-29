package com.example.wanandroid.ui.main.project

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.databinding.FragmentProjectBinding
import com.example.wanandroid.model.ProjectTree
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    private val mViewModel by viewModels<ProjectViewModel>()
    private lateinit var mList: List<ProjectTree>

    override val layoutId = R.layout.fragment_project

    override fun initView() {

        mViewModel.projectTreeEvent.observe(this, {
            mList = it
            initViewPager()
        })
    }

    private fun initViewPager() {
        mBinding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = mList.size

            override fun createFragment(position: Int): Fragment {
                return ContentFragment.newInstance(mList[position].id)
            }
        }
        TabLayoutMediator(mBinding.tabLayout, mBinding.pager) { tab, position ->
            tab.text = mList[position].name
        }.attach()
    }


}