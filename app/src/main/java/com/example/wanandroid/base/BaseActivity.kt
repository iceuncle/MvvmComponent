package com.example.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        initView()
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()

}