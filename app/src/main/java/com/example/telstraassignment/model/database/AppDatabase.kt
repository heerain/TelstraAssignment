package com.example.telstraassignment.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.telstraassignment.model.FactsDao
import com.example.telstraassignment.model.FactsResponse

@Database(entities = arrayOf(FactsResponse::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun facstDao() : FactsDao
}