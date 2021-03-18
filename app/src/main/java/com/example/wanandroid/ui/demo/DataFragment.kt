package com.example.wanandroid.ui.demo

import androidx.fragment.app.viewModels
import com.example.wanandroid.MyApplication
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.databinding.FragmentDataBinding


class DataFragment : BaseFragment<FragmentDataBinding>() {

    companion object {
        fun newInstance() = DataFragment()
    }

    private val mViewModel: DataViewModel by viewModels {
        DataViewModelFactory((context?.applicationContext as MyApplication).databaseRepo)
    }

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