package com.example.module_project.viewmodel

import androidx.lifecycle.MutableLiveData
import autodispose2.autoDispose
import com.example.module_base.base.BaseViewModel
import com.example.module_base.model.BaseResponse
import com.example.module_base.model.ProjectTree
import com.example.module_base.net.BaseObserver
import com.example.module_base.repos.DatabaseRepository
import com.example.module_base.repos.NetRepository
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