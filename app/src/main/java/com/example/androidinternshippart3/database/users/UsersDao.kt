package com.example.androidinternshippart3.database.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {
    @Insert
    suspend fun insert(user: Users)

    @Update
    suspend fun update(user: Users)

    @Query("Select * from users_table WHERE usersId = :key")
    suspend fun get(key: Long): Users?

    @Query("DELETE from users_table where usersId = :key ")
    suspend fun delete(key: Long)

    @Query("DELETE FROM users_table")
    suspend fun clear()

    @Query("Select * from users_table WHERE login = :key")
    suspend fun getByLogin(key: String): Users?


}
