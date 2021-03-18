package com.example.wanandroid.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchKey")
data class SearchKey(@PrimaryKey val key: String)

//@PrimaryKey(autoGenerate = true) val id: Int