package com.example.module_base

import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter


open class BaseApplication : MultiDexApplication() {
    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}

