package com.example.androidinternshippart3.database.tests

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TestsDao {
    @Insert
    suspend fun insert(test:Tests)

    @Update
    suspend fun update(test: Tests)

    @Query("Select * from test_table WHERE testId = :key")
    suspend fun get(key: Long): Tests?

    @Query("DELETE FROM test_table")
    suspend fun clear()


}