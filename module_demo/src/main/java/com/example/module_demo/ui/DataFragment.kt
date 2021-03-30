package com.example.module_demo.ui

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.base.BaseFragment
import com.example.module_demo.R
import com.example.module_demo.databinding.FragmentDataBinding
import com.example.module_demo.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 示例Demo
 */
@AndroidEntryPoint
@Route(path = Constant.ROUTE_DATA)
class DataFragment : BaseFragment<FragmentDataBinding>() {

    private val mViewModel: DataViewModel by viewModels()

    override val layoutId: Int get() = R.layout.fragment_data

    override fun initView() {
        mBinding.addBtn.setOnClickListener { mViewModel.insert("key${(0..10).random()}\n") }
        mBinding.deleteAllBtn.setOnClickListener { mViewModel.deleteAll() }

        mViewModel.allKeys.observe(this, {
            var str = ""
            for (s in it) {
                str += s.key
            }
            mBinding.contentTv.text = str
        })

    }


}