package com.example.module_demo.ui

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.base.BasePageFragment
import com.example.module_base.model.Article
import com.example.module_demo.R
import com.example.module_demo.databinding.FragmentDemoBinding
import com.example.module_demo.viewmodel.DemoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 示例Demo
 */
@AndroidEntryPoint
@Route(path = Constant.ROUTE_DEMO)
class DemoFragment : BasePageFragment<Article, FragmentDemoBinding>() {

    private var mPosition = 0

    private val mViewModel: DemoViewModel by viewModels()
    private val mAdapter: DemoAdapter<Article, DemoAdapter.ViewHolder> by lazy { DemoAdapter() }

    override val layoutId: Int get() = R.layout.fragment_demo

    override fun providePageViewModel() = mViewModel
    override fun provideRecyclerView() = mBinding.recyclerView
    override fun provideRefreshLayout() = mBinding.refreshLayout
    override fun provideEmptyView() = mBinding.include.emptyView
    override fun provideAdapter() = mAdapter

    override fun initDetailView() {
        mPosition = arguments?.getInt("position") ?: 0

        mBinding.recyclerView.adapter = mAdapter

        mBinding.refreshLayout.setColorSchemeResources(R.color.purple_200)
        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.dataSource?.invalidate()
        }
    }


}