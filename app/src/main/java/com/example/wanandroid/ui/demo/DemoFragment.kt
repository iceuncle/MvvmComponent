package com.example.wanandroid.ui.demo

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.base.BasePageFragment
import com.example.wanandroid.databinding.FragmentDashboardBinding
import com.example.wanandroid.model.Article
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * 示例Demo
 */
@AndroidEntryPoint
class DemoFragment : BasePageFragment<Article, FragmentDashboardBinding, DemoAdapter.ViewHolder>(), OnRefreshListener {

    private var mPosition = 0

    private val mViewModel: DemoViewModel by viewModels()
    private val mAdapter: DemoAdapter<Article, DemoAdapter.ViewHolder> by lazy { DemoAdapter() }

    override val layoutId: Int get() = R.layout.fragment_dashboard

    override fun providePageViewModel() = mViewModel
    override fun provideRecyclerView() = mBinding.recyclerView
    override fun provideRefreshLayout() = mBinding.refreshLayout
    override fun provideEmptyView() = mBinding.emptyView
    override fun provideAdapter(): PagedListAdapter<Article, out RecyclerView.ViewHolder> = mAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPosition = arguments?.getInt("position") ?: 0
        println("mPosition+  onCreate $mPosition....")
    }

    override fun initDetailView() {
        mPosition = arguments?.getInt("position") ?: 0
        println("mPosition+  $mPosition")

        mBinding.refreshLayout.setEnableRefresh(true)
        mBinding.refreshLayout.setEnableLoadMore(false)
        mBinding.refreshLayout.setOnRefreshListener(this)

        mBinding.recyclerView.adapter = mAdapter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.dataSource?.invalidate()
    }

}