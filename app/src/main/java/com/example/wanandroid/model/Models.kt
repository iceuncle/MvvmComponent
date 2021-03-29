package com.example.wanandroid.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.wanandroid.BR


data class BaseResponse<T>(val data: T, val errorCode: Int, val errorMsg: String?)

data class PageData<T>(val curPage: Int, val datas: ArrayList<T>, val offset: Int,
                       val over: Boolean, val size: Int, val total: Int)

data class ArticleBean(
        val author: String,
        val id: Int,
        val title: String,
        val superChapterName: String,
        val niceShareDate: String,
        val shareUser: String,
        val link: String,
        val envelopePic: String
) : BaseObservable() {
    @Bindable
    var topFlag: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.topFlag)
        }

    @Bindable
    var collect: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.collect)
        }
}

data class ProjectTree(val id: Int, var name: String)

data class User(val nickname: String, val username: String, val token: String, val id: Int)