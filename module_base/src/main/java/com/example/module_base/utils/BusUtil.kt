package com.example.module_base.utils

import androidx.lifecycle.MutableLiveData


object BusUtil {

    @JvmStatic
    var failMessage = MutableLiveData<String>()

    @JvmStatic
    var showProgress = MutableLiveData<Boolean>()
}

