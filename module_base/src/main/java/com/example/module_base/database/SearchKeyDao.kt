package com.example.module_base.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.module_base.database.entity.SearchKey
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchKeyDao {

    @Query("Select * from SearchKey")
    fun getSearchKeys(): Flow<List<SearchKey>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(key: SearchKey)

    @Query("DELETE FROM SearchKey")
    suspend fun deleteAll()
}