package com.example.wanandroid.utils

import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.showProgress(): Single<T> = compose {
    it.doOnSubscribe { BusUtil.showProgress.postValue(true) }
            .doAfterTerminate { BusUtil.showProgress.postValue(false) }
}
