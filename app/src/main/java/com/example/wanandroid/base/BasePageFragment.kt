package com.example.wanandroid.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout

abstract class BasePageFragment<T, VB : ViewDataBinding, VH : RecyclerView.ViewHolder> : BaseFragment<VB>() {

    protected val mPageViewModel: BasePageViewModel<T> by lazy { providePageViewModel() }
    protected val mRecyclerView: RecyclerView by lazy { provideRecyclerView() }
    protected val mRefreshLayout: SmartRefreshLayout by lazy { provideRefreshLayout() }
    protected val mEmptyView: View by lazy { provideEmptyView() }
    //mPageAdapter 不能使用lazy加载，不然在submitList回调时，mPageAdapter还未初始化
    protected val mPageAdapter: PagedListAdapter<T, out RecyclerView.ViewHolder> by lazy { provideAdapter() }


    override fun initView() {
        initDetailView()

        //触发页面初始化数据加载的逻辑
        mPageViewModel.pageData.observe(this, this::submitList)
        //监听分页时有无更多数据,以决定是否关闭上拉加载的动画
        mPageViewModel.boundaryPageData.observe(this, this::finishRefresh)
    }

    protected abstract fun providePageViewModel(): BasePageViewModel<T>
    protected abstract fun provideRecyclerView(): RecyclerView
    protected abstract fun provideRefreshLayout(): SmartRefreshLayout
    protected abstract fun provideEmptyView(): View
    protected abstract fun provideAdapter(): PagedListAdapter<T, out RecyclerView.ViewHolder>
    protected abstract fun initDetailView()

    private fun submitList(result: PagedList<T>?) {
        mPageAdapter.submitList(result)
    }

    private fun finishRefresh(hasData: Boolean) {
        val state = mRefreshLayout.state
        if (state.isFooter && state.isOpening) {
            mRefreshLayout.finishLoadMore()
        } else if (state.isHeader && state.isOpening) {
            mRefreshLayout.finishRefresh()
        }
        val currentList: PagedList<T>? = mPageAdapter.currentList
        val isHasData = hasData || currentList?.size ?: 0 > 0
        mEmptyView.visibility = if (isHasData) View.GONE else View.VISIBLE
    }
}