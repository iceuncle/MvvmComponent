package com.example.wanandroid.ui.login

import android.text.TextUtils
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import autodispose2.autoDispose
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.model.BaseResponse
import com.example.wanandroid.model.User
import com.example.wanandroid.net.BaseObserver
import com.example.wanandroid.repos.NetRepository
import com.example.wanandroid.utils.BusUtil
import com.example.wanandroid.utils.showProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
        val netRepo: NetRepository
) : BaseViewModel() {

    val phone = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var enableLogin = MediatorLiveData<Boolean>()

    var loginResult = MutableLiveData<User>()
    var registerResult = MutableLiveData<User>()

    init {
        val observer = Observer { s: String? ->
            if (TextUtils.isEmpty(phone.value) || TextUtils.isEmpty(password.value)) {
                enableLogin.postValue(false)
            } else {
                enableLogin.postValue(true)
            }
        }
        enableLogin.addSource(phone, observer)
        enableLogin.addSource(password, observer)
    }

    fun register() {
        netRepo.register(phone.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .showProgress()
                .autoDispose(this)
                .subscribe(object : BaseObserver<BaseResponse<User?>?>() {
                    override fun onSuccess(t: BaseResponse<User?>?) {
                        if (t?.data == null) {
                            BusUtil.failMessage.postValue(t?.errorMsg)
                        } else {
                            registerResult.postValue(t.data)
                        }
                    }
                })
    }

    fun login() {
        netRepo.login(phone.value!!, password.value!!)
                .subscribeOn(Schedulers.io())
                .showProgress()
                .autoDispose(this)
                .subscribe(object : BaseObserver<BaseResponse<User?>?>() {
                    override fun onSuccess(t: BaseResponse<User?>?) {
                        if (t?.data == null) {
                            BusUtil.failMessage.postValue(t?.errorMsg)
                        } else {
                            loginResult.postValue(t.data)
                        }
                    }
                })
    }


}