package com.example.androidinternshippart3.database.access

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidinternshippart3.database.users.Users

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

    @Query("SELECT * FROM access_table ORDER BY accessId ASC")
    suspend fun getAllAccess(): List<Access>

}
