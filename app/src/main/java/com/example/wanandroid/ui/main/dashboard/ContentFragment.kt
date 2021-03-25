package com.example.wanandroid.ui.main.dashboard

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BasePageFragment
import com.example.wanandroid.databinding.FragmentContentBinding
import com.example.wanandroid.model.Article
import com.example.wanandroid.model.ArticleBean
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContentFragment : BasePageFragment<ArticleBean, FragmentContentBinding>() {

    companion object {
        fun newInstance(cid: Int) =
                ContentFragment().apply {
                    arguments = Bundle().apply { putInt("cid", cid) }
                }
    }

    @Inject
    lateinit var assistedFactory: ContentViewModel.AssistedFactory

    private val mViewModel: ContentViewModel by viewModels {
        ContentViewModel.provideFactory(assistedFactory, arguments?.getInt("cid", 0)!!)
    }

    private val mAdapter: ContentAdapter<Article, ContentAdapter.ViewHolder> by lazy { ContentAdapter() }


    override val layoutId = R.layout.fragment_content

    override fun providePageViewModel() = mViewModel

    override fun provideRecyclerView() = mBinding.recyclerView

    override fun provideRefreshLayout() = mBinding.refreshLayout

    override fun provideEmptyView() = mBinding.include.emptyView

    override fun provideAdapter() = mAdapter

    override fun initDetailView() {

        mBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mBinding.recyclerView.adapter = mAdapter

        mBinding.refreshLayout.setColorSchemeResources(R.color.purple_200)
        mBinding.refreshLayout.setOnRefreshListener {
            mViewModel.dataSource?.invalidate()
        }
    }

}