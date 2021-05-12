package com.example.androidinternshippart3.database.results

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ResultsDao {
    @Insert
    suspend fun insert(results: Results)

    @Update
    suspend fun update(results: Results)

    @Query("Select * from results_table WHERE user = :key")
    suspend fun get(key: Long): Results?

    @Query("DELETE FROM results_table")
    suspend fun clear()
}