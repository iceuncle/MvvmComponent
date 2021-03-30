package com.example.module_main.ui

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.base.BasePageFragment
import com.example.module_base.model.Article
import com.example.module_base.model.ArticleBean
import com.example.module_main.R
import com.example.module_main.databinding.FragmentHomeBinding
import com.example.module_main.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Constant.ROUTE_HOME)
class HomeFragment : BasePageFragment<ArticleBean, FragmentHomeBinding>() {

    override val layoutId = R.layout.fragment_home

    private val mViewModel: HomeViewModel by viewModels()
    private val mAdapter: HomeAdapter<Article, HomeAdapter.ViewHolder> by lazy { HomeAdapter() }


    override fun providePageViewModel() = mViewModel

    override fun provideRecyclerView() = mBinding.recyclerView

    override fun provideRefreshLayout() = mBinding.refreshLayout

    override fun provideEmptyView() = mBinding.include.emptyView

    override fun provideAdapter() = mAdapter


    override fun initDetailView() {
        mBinding.recyclerView.adapter = mAdapter

        mBinding.refreshLayout.setColorSchemeResources(R.color.purple_200)
        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.dataSource?.invalidate()
        }

        mAdapter.ivCollectClick = {
            it.collect = !it.collect
        }
    }

}