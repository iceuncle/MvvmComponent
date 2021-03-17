package com.example.wanandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.BoundaryCallback


abstract class BasePageViewModel<T> : ViewModel {

    companion object {
        //分页size
        const val PAGE_SIZE = 20
        //第一次加载数量
        const val INITIAL_LOAD_SIZE = 22
        //预加载数量
        const val PREFETCH_DISTANCE = 3
        //第一次加载的key
        const val INITIAL_LOAD_KEY = 0
    }

    var dataSource: DataSource<Int, T>? = null

    val pageData: LiveData<PagedList<T>?>
    //是否有数据
    val boundaryPageData = MutableLiveData<Boolean>()

    constructor() : this(PAGE_SIZE, INITIAL_LOAD_SIZE, PREFETCH_DISTANCE)

    constructor(pageSize: Int, initialLoadSize: Int, prefetchDistance: Int) {
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(initialLoadSize)
                .setPrefetchDistance(prefetchDistance)
                .build()
        pageData = LivePagedListBuilder(factory, config)
                .setInitialLoadKey(INITIAL_LOAD_KEY)
                .setBoundaryCallback(callback)
                .build()
    }

    private var callback: BoundaryCallback<T> = object : BoundaryCallback<T>() {
        override fun onZeroItemsLoaded() {
            //新提交的PagedList中没有数据
            boundaryPageData.postValue(false)
        }

        override fun onItemAtFrontLoaded(itemAtFront: T) {
            //新提交的PagedList中第一条数据被加载到列表上
            boundaryPageData.postValue(true)
        }

        override fun onItemAtEndLoaded(itemAtEnd: T) {
            //新提交的PagedList中最后一条数据被加载到列表上
        }
    }

    private val factory: DataSource.Factory<Int, T> = object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            if (dataSource == null || dataSource!!.isInvalid) {
                dataSource = createDataSource()
            }
            return dataSource!!
        }
    }

    abstract fun createDataSource(): DataSource<Int, T>

    fun postBoundaryPageData(value: Boolean) {
        boundaryPageData.postValue(value)
    }

}