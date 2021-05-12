package com.example.androidinternshippart3.database.question

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionsDao {
    @Insert
    suspend fun insert(questions: Questions)

    @Update
    suspend fun update(questions: Questions)

    @Query("Select * from question_table WHERE questionId = :key")
    suspend fun get(key:Long) : Questions?

    @Query("Delete from question_table")
    suspend fun clear()

}