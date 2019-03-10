package com.example.telstraassignment.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface FactsDao {

    @get:Query("SELECT * FROM factsResponse")
    val all: List<FactsResponse>

    @Insert
    fun insertAll(vararg posts: FactsResponse)
}