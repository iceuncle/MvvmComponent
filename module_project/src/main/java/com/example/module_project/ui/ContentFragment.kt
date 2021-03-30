package com.example.module_project.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.module_base.base.BasePageFragment
import com.example.module_base.model.Article
import com.example.module_base.model.ArticleBean
import com.example.module_project.R
import com.example.module_project.databinding.FragmentContentBinding
import com.example.module_project.viewmodel.ContentViewModel
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