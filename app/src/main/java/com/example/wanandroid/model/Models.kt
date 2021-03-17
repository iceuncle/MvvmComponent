package com.example.wanandroid.model


data class BaseResponse<T>(var data: T? = null, var errorCode: Int = 0, var errorMsg: String? = null)

data class PageData<T>(var curPage: Int = 0, var datas: List<T>? = null, var offset: Int = 0,
                       var over: Boolean = false, var size: Int = 0, var total: Int = 0)