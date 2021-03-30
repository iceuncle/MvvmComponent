package com.example.module_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import autodispose2.autoDispose
import com.example.module_base.base.BaseObserver
import com.example.module_base.base.BasePageViewModel
import com.example.module_base.model.ArticleBean
import com.example.module_base.repos.NetRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ContentViewModel @AssistedInject constructor(
        private val netRep: NetRepository,
        @Assisted val cid: Int
) : BasePageViewModel<ArticleBean>() {

    override fun loadData(page: Int, callback: (data: List<ArticleBean>) -> Unit) {
        netRep.getProjectListByCid(page, cid)
                .map { it.data.datas }
                .autoDispose(this)
                .subscribe(object : BaseObserver<ArrayList<ArticleBean>>() {
                    override fun onSuccess(t: ArrayList<ArticleBean>) {
                        callback(t)
                        postBoundaryPageData(t.isNotEmpty())
                    }

                    override fun onFailure(e: Throwable?) {
                        super.onFailure(e)
                        postBoundaryPageData(false)
                    }
                })
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(cid: Int): ContentViewModel
    }

    companion object {
        fun provideFactory(assistedFactory: AssistedFactory,
                           cid: Int): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(cid) as T
            }
        }
    }
}
