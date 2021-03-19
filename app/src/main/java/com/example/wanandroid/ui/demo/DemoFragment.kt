package com.example.wanandroid.ui.demo

import androidx.fragment.app.viewModels
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.base.BasePageFragment
import com.example.wanandroid.databinding.FragmentDashboardBinding
import com.example.wanandroid.model.Article
import dagger.hilt.android.AndroidEntryPoint

/**
 * 示例Demo
 */
@AndroidEntryPoint
class DemoFragment : BasePageFragment<Article, FragmentDashboardBinding, DemoAdapter.ViewHolder>() {

    private var mPosition = 0

    private val mViewModel: DemoViewModel by viewModels()
    private val mAdapter: DemoAdapter<Article, DemoAdapter.ViewHolder> by lazy { DemoAdapter() }

    override val layoutId: Int get() = R.layout.fragment_dashboard

    override fun providePageViewModel() = mViewModel
    override fun provideRecyclerView() = mBinding.recyclerView
    override fun provideRefreshLayout() = mBinding.refreshLayout
    override fun provideEmptyView() = mBinding.emptyView
    override fun provideAdapter(): PagedListAdapter<Article, out RecyclerView.ViewHolder> = mAdapter

    override fun initDetailView() {
        mPosition = arguments?.getInt("position") ?: 0

        mBinding.recyclerView.adapter = mAdapter

        mBinding.refreshLayout.setColorSchemeResources(R.color.purple_200)
        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.dataSource?.invalidate()
        }
    }


}