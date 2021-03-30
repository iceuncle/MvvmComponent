package com.example.module_base.repos

import androidx.annotation.WorkerThread
import com.example.module_base.database.SearchKeyDao
import com.example.module_base.database.entity.SearchKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DatabaseRepository @Inject constructor(private val searchKeyDao: SearchKeyDao) {

    val searchKeys: Flow<List<SearchKey>> = searchKeyDao.getSearchKeys()

    @WorkerThread
    suspend fun insert(key: String) {
        searchKeyDao.insert(SearchKey(key))
    }

    @WorkerThread
    suspend fun deleteAll() {
        searchKeyDao.deleteAll()
    }
}