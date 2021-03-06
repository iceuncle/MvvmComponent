package com.example.module_demo.viewmodel

import androidx.paging.ItemKeyedDataSource
import autodispose2.autoDispose
import com.example.module_base.base.BaseObserver
import com.example.module_base.base.BasePageViewModel
import com.example.module_base.model.Article
import com.example.module_base.model.BaseResponse
import com.example.module_base.model.PageData
import com.example.module_base.repos.NetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(private val netRepo: NetRepository) : BasePageViewModel<Article>() {


//    override fun createDataSource(): DataSource<Int, Article> = PageDataSource()

//    //普通分页
//    inner class PageDataSource : PageKeyedDataSource<Int, Article>() {
//        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
//            loadData(0, callback, null)
//        }
//
//        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
//        }
//
//        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
//            loadData(params.key + 1, null, callback)
//        }
//    }
//
//    override fun loadData(page: Int, initialCallback: PageKeyedDataSource.LoadInitialCallback<Int, Article>?,
//                         callback: PageKeyedDataSource.LoadCallback<Int, Article>?) {
//        netRepo.getArticles(page)
//                .autoDispose(this)
//                .subscribe(object : BaseObserver<BaseResponse<PageData<Article>>?>() {
//                    override fun onSuccess(t: BaseResponse<PageData<Article>>?) {
//                        if (initialCallback != null) {
//                            initialCallback.onResult(t?.data?.datas ?: emptyList(), -1, 0)
//                        } else {
//                            callback?.onResult(t?.data?.datas ?: emptyList(), page)
//                        }
//                        postBoundaryPageData(t?.data?.datas?.size ?: 0 > 0)
//                    }
//
//                    override fun onFailure(e: Throwable?) {
//                        postBoundaryPageData(false)
//                    }
//                })
//    }


    //Feeds流选用ItemKeyedDataSource
    inner class FeedDataSource : ItemKeyedDataSource<Int, Article>() {
        override fun getKey(item: Article): Int = item.id

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Article>) {
            //加载初始化数据的
            loadData(params.requestedInitialKey ?: 0, params.requestedLoadSize, callback)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Article>) {
            //向后加载分页数据的
            loadData(params.key, params.requestedLoadSize, callback)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Article>) {
            //能够向前加载数据的
            callback.onResult(emptyList())
        }
    }

    private fun loadData(page: Int, pageSize: Int, callback: ItemKeyedDataSource.LoadCallback<Article>) {
        netRepo.getArticles(page)
                .autoDispose(this)
                .subscribe(object : BaseObserver<BaseResponse<PageData<Article>>?>() {
                    override fun onSuccess(t: BaseResponse<PageData<Article>>?) {
                        callback.onResult(t?.data?.datas ?: emptyList())
                        postBoundaryPageData(t?.data?.datas?.size ?: 0 > 0)
                    }

                    override fun onFailure(e: Throwable?) {
                        postBoundaryPageData(false)
                    }
                })
    }

    override fun loadData(page: Int, callback: (data: List<Article>) -> Unit) {
        netRepo.getArticles(page)
                .autoDispose(this)
                .subscribe(object : BaseObserver<BaseResponse<PageData<Article>>?>() {
                    override fun onSuccess(t: BaseResponse<PageData<Article>>?) {
                        callback(t?.data?.datas ?: emptyList())
                        postBoundaryPageData(t?.data?.datas?.size ?: 0 > 0)
                    }

                    override fun onFailure(e: Throwable?) {
                        postBoundaryPageData(false)
                    }
                })
    }


}