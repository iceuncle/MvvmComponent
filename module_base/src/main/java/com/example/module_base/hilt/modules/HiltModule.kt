package com.example.module_base.hilt.modules

import Constant
import android.content.Context
import com.example.lib_net.NetClient
import com.example.module_base.database.AppDataBase
import com.example.module_base.repos.NetService
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
    fun provideNetService() = NetClient.create<NetService>(Constant.REQUEST_BASE_URL)

}