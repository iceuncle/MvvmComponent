package com.example.module_base.utils

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter


object RouteCenter {
    fun navigate(path: String, bundle: Bundle? = null): Any? {
        val build = ARouter.getInstance().build(path)
        return if (bundle == null) build.navigation() else build.with(bundle).navigation()
    }
}