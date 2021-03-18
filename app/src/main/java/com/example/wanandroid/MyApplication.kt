package com.example.wanandroid

import androidx.multidex.MultiDexApplication
import com.example.wanandroid.database.AppDataBase
import com.example.wanandroid.repos.DatabaseRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : MultiDexApplication() {
    companion object {

    }
    val database by lazy { AppDataBase.getDatabase(this) }
    val databaseRepo by lazy { DatabaseRepository(database.searchKeyDao()) }
}

