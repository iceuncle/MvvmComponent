package com.example.module_base.net

import com.example.module_base.utils.BusUtil
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException


abstract class BaseObserver<T> : SingleObserver<T> {
    override fun onSubscribe(d: Disposable?) {
    }

    override fun onError(e: Throwable?) {
        if (e == null) return
        val msg = e.message!!
        when {
            isHttpError(msg) -> {
                onFailure(Throwable("network error"))
            }
            e is HttpException -> {
                when (e.code()) {
                    else -> onFailure(e)
                }
            }
            else -> {
                onFailure(e)
            }
        }
    }

    open fun onFailure(e: Throwable?) {
        BusUtil.failMessage.postValue(e?.message ?: "")
    }

    private fun isHttpError(msg: String): Boolean {
        val errorArray = arrayOf(
                "timeout",
                "java.net.ConnectException",
                "java.net.SocketTimeoutException",
                "failed",
                "Failed to connect to",
                "stream was reset",
                "Unable to resolve host",
                "SSL handshake time out",
                "time out"
        )
        for (error in errorArray) {
            if (msg.contains(error)) return true
        }
        return false
    }
}