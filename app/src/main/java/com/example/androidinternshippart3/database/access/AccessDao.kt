package com.example.androidinternshippart3.database.access

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccessDao {
    @Insert
    suspend fun insert(access:Access)

    @Update
    suspend fun update(access:Access)

    @Query("Select * from access_table WHERE accessId = :key")
    suspend fun get(key:Long) : Access ?

    @Query("DELETE from access_table where accessId = :key ")
    suspend fun delete(key:Long)
}
