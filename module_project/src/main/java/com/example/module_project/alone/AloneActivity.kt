package com.example.module_project.alone

import com.example.module_base.base.BaseActivity
import com.example.module_base.databinding.CommonContainerBinding
import com.example.module_project.R
import com.example.module_project.ui.ProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AloneActivity : BaseActivity<CommonContainerBinding>() {
    override val layoutId = R.layout.common_container

    override fun initView() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, ProjectFragment())
                .commitNow()
    }

}