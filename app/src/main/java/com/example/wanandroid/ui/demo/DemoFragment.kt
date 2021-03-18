package com.example.wanandroid.ui.demo

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.base.BasePageFragment
import com.example.wanandroid.databinding.FragmentDashboardBinding
import com.example.wanandroid.model.Article
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


class DemoFragment : BasePageFragment<Article, FragmentDashboardBinding, DemoAdapter.ViewHolder>(), OnRefreshListener {

    private var mPosition = 0

    companion object {
        fun newInstance(position: Int) = DemoFragment().apply {
            arguments = Bundle().apply {
                putInt("position", position)
            }
        }
    }

    private val mViewModel: DemoViewModel by lazy { ViewModelProvider(this).get(DemoViewModel::class.java) }
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

        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerView.itemAnimator = null
        mBinding.recyclerView.adapter = mAdapter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.dataSource?.invalidate()
    }

}