package com.example.module_demo.alone

import com.example.module_base.base.BaseActivity
import com.example.module_base.databinding.CommonContainerBinding
import com.example.module_demo.R
import com.example.module_demo.ui.DataFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AloneActivity : BaseActivity<CommonContainerBinding>() {

    override val layoutId = R.layout.common_container

    override fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, DataFragment())
                .commitNow()
    }

}