package com.example.wanandroid.ui.main.project

import androidx.lifecycle.MutableLiveData
import autodispose2.autoDispose
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.model.BaseResponse
import com.example.wanandroid.model.ProjectTree
import com.example.wanandroid.net.BaseObserver
import com.example.wanandroid.repos.DatabaseRepository
import com.example.wanandroid.repos.NetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
        private val netRep: NetRepository,
        private val dataRepo: DatabaseRepository
) : BaseViewModel() {

    init {
        netRep.getProjectTree()
                .subscribeOn(Schedulers.io())
                .autoDispose(this)
                .subscribe(object : BaseObserver<BaseResponse<List<ProjectTree>>>() {
                    override fun onSuccess(t: BaseResponse<List<ProjectTree>>) {
                        projectTreeEvent.postValue(t.data)
                    }
                })
    }

    val projectTreeEvent = MutableLiveData<List<ProjectTree>>()


}