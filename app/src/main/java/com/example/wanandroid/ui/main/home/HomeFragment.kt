package com.example.wanandroid.ui.main.home

import androidx.fragment.app.viewModels
import com.example.wanandroid.R
import com.example.wanandroid.base.BasePageFragment
import com.example.wanandroid.databinding.FragmentHomeBinding
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.ArticleBean
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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