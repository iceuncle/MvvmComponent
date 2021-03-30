package com.example.module_login.ui

import android.view.View
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.base.BaseActivity
import com.example.module_base.utils.BusUtil
import com.example.module_login.R
import com.example.module_login.databinding.ActivityLoginBinding
import com.example.module_login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = Constant.ROUTE_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mViewModel: LoginViewModel by viewModels()

    override val layoutId = R.layout.activity_login

    override fun initView() {

        mBinding.vm = mViewModel

        mBinding.loginBtn.setOnClickListener { mViewModel.login() }
        mBinding.registerBtn.setOnClickListener { mViewModel.register() }

        mViewModel.enableLogin.observe(this) {
            mBinding.registerBtn.isEnabled = it
            mBinding.loginBtn.isEnabled = it
        }

        mViewModel.loginResult.observe(this) {
            BusUtil.failMessage.postValue("Login Success")
            finish()
        }
        mViewModel.registerResult.observe(this) {
            BusUtil.failMessage.postValue("Register Success")
        }

        BusUtil.showProgress.observe(this) {
            mBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

}