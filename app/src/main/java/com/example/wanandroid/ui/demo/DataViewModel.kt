package com.example.wanandroid.ui.demo

import androidx.lifecycle.*
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.database.entity.SearchKey
import com.example.wanandroid.repos.DatabaseRepository
import kotlinx.coroutines.launch


class DataViewModel(private val dataRepo: DatabaseRepository) : BaseViewModel() {

    val allKeys: LiveData<List<SearchKey>> = dataRepo.searchKeys.asLiveData()

    fun insert(key: String) = viewModelScope.launch {
        dataRepo.insert(key)
    }

    fun deleteAll() = viewModelScope.launch {
        dataRepo.deleteAll()
    }
}

class DataViewModelFactory(private val dataRepo: DatabaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(dataRepo) as T
    }

}