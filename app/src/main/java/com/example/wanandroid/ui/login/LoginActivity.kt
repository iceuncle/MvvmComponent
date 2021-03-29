package com.example.wanandroid.ui.login

import android.view.View
import androidx.activity.viewModels
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.ActivityLoginBinding
import com.example.wanandroid.utils.BusUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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