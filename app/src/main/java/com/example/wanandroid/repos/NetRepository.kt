package com.example.wanandroid.repos

import com.example.wanandroid.model.Article
import com.example.wanandroid.model.BaseResponse
import com.example.wanandroid.model.PageData
import com.example.wanandroid.net.NetService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class NetRepository @Inject constructor(private var netService: NetService) {

    fun getArticles(page: Int): Single<BaseResponse<PageData<Article>>> {
        return netService.getArticles(page.toString())
    }

}
