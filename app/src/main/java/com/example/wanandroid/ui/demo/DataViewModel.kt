package com.example.wanandroid.ui.demo

import androidx.lifecycle.*
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.database.entity.SearchKey
import com.example.wanandroid.repos.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
        private val databaseRepo: DatabaseRepository
) : BaseViewModel() {

    val allKeys: LiveData<List<SearchKey>> = databaseRepo.searchKeys.asLiveData()

    fun insert(key: String) = viewModelScope.launch {
        databaseRepo.insert(key)
    }

    fun deleteAll() = viewModelScope.launch {
        databaseRepo.deleteAll()
    }

}

//viewModel传参需要使用ViewModelProvider.Factory
//class DataViewModelFactory @Inject constructor(private val dataRepo: DatabaseRepository)
//    : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return DataViewModel(dataRepo) as T
//    }
//}