package com.example.wanandroid.net

import com.example.wanandroid.model.Article
import com.example.wanandroid.model.BaseResponse
import com.example.wanandroid.model.PageData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface NetService {
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page: String?): Observable<BaseResponse<PageData<Article>>>
}