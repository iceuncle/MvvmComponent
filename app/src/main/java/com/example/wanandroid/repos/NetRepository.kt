package com.example.wanandroid.repos

import com.example.wanandroid.model.Article
import com.example.wanandroid.model.BaseResponse
import com.example.wanandroid.model.PageData
import com.example.wanandroid.net.NetClient
import com.example.wanandroid.net.NetService
import io.reactivex.rxjava3.core.Observable


class NetRepository private constructor(private var netService: NetService) {

    fun getArticles(page: Int): Observable<BaseResponse<PageData<Article>>> {
        return netService.getArticles(page.toString())
    }

    companion object {
       var instance = NetRepository(NetClient.create())
    }

}
