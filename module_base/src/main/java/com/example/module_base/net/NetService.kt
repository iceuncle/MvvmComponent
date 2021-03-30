package com.example.module_base.net

import com.example.module_base.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


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

    @FormUrlEncoded
    @POST("/user/register")
    fun register(@Field("username") username: String, @Field("password") password: String,
                 @Field("repassword") repassword: String): Single<BaseResponse<User?>>

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Single<BaseResponse<User?>>

}