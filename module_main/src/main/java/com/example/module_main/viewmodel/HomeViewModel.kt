package com.example.module_main.viewmodel

import autodispose2.autoDispose
import com.example.module_base.base.BaseObserver
import com.example.module_base.base.BasePageViewModel
import com.example.module_base.model.ArticleBean
import com.example.module_base.repos.NetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val netRepo: NetRepository
) : BasePageViewModel<ArticleBean>() {

    override fun loadData(page: Int, callback: (data: List<ArticleBean>) -> Unit) {
        val single = if (page == 0)
            Single.zip(netRepo.getTopArticles(), netRepo.getArticleList(page), { t1, t2 ->
                t1.data.forEach { data -> data.topFlag = true }
                t2.data.datas.addAll(0, t1.data)
                t2.data.datas
            })
        else netRepo.getArticleList(page).map { it.data.datas }

        single.autoDispose(this)
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

}