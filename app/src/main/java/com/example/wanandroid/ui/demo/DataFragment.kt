package com.example.wanandroid.ui.demo

import androidx.fragment.app.viewModels
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.databinding.FragmentDataBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 示例Demo
 */
@AndroidEntryPoint
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