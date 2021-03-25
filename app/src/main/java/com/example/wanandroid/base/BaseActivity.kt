package com.example.wanandroid.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.wanandroid.utils.BusUtil


abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        BusUtil.failMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        initView()
    }

    protected abstract val layoutId: Int
    protected abstract fun initView()

}