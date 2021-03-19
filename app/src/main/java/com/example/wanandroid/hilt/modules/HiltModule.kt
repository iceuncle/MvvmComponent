package com.example.wanandroid.hilt.modules

import android.content.Context
import com.example.wanandroid.database.AppDataBase
import com.example.wanandroid.net.NetClient
import com.example.wanandroid.net.NetService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDataBase.getDatabase(context)

    @Singleton
    @Provides
    fun provideSearchKeyDao(dataBase: AppDataBase) = dataBase.searchKeyDao()

    @Singleton
    @Provides
    fun provideNetService() = NetClient.create<NetService>()

}