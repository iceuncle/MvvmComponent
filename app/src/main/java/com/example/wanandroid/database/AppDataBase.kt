package com.example.wanandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wanandroid.database.entity.SearchKey

@Database(entities = [SearchKey::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun searchKeyDao(): SearchKeyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context,
                        AppDataBase::class.java, "app_database")
//                        //允许在主线程进行查询
//                        .allowMainThreadQueries()
//                        //数据库创建和打开后的回调
//                        .addCallback(sRoomDatabaseCallback)
//                        //设置查询的线程池
//                        .setQueryExecutor()
//                        .openHelperFactory()
//                        //room的日志模式
//                        .setJournalMode()
//                        //数据库升级异常之后的回滚
//                        .fallbackToDestructiveMigration()
//                        //数据库升级异常后根据指定版本进行回滚
//                        .fallbackToDestructiveMigrationFrom()
//                        //数据库升级兼容语句
//                        .addMigrations(AppDataBase.MIGRATION_1_2)
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}