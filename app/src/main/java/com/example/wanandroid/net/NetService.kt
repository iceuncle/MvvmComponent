package com.example.wanandroid.net

import com.example.wanandroid.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NetService {
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Single<BaseResponse<PageData<Article>>>

    @GET("/article/top/json")
    fun getTopArticles(): Single<BaseResponse<ArrayList<ArticleBean>>>

    @GET("/article/list/{page}/json ")
    fun getArticleList(@Path("page") page: Int): Single<BaseResponse<PageData<ArticleBean>>>

    @GET("project/tree/json")
    fun getProjectTree(): Single<BaseResponse<List<ProjectTree>>>

    @GET("project/list/{page}/json")
    fun getProjectListByCid(@Path("page") page: Int, @Query("cid") cid: Int): Single<BaseResponse<PageData<ArticleBean>>>

}