package com.example.wanandroid.repos

import androidx.annotation.WorkerThread
import com.example.wanandroid.database.SearchKeyDao
import com.example.wanandroid.database.entity.SearchKey
import kotlinx.coroutines.flow.Flow


class DatabaseRepository(private val searchKeyDao: SearchKeyDao) {

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