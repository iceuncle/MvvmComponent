package com.example.wanandroid.net

import com.example.wanandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetClient {

    companion object {
        const val CONNECT_TIME_OUT = 10L
        const val READ_TIME_OUT = 10L
        const val WRITE_TIME_OUT = 10L

        @Volatile
        var instance: NetClient? = null
            get() {
                if (field == null) {
                    synchronized(NetClient::class.java) {
                        if (field == null) {
                            field = NetClient()
                        }
                    }
                }
                return field
            }
            private set

        inline fun <reified T> create(): T {
            return create("https://www.wanandroid.com")
        }

        inline fun <reified T> create(baseUrl: String): T {
            return instance!!.getRetrofit(baseUrl).create(T::class.java)
        }
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}
