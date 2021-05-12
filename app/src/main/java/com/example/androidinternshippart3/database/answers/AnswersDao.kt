package com.example.androidinternshippart3.database.answers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidinternshippart3.database.question.Questions

@Dao
interface AnswersDao {
    @Insert
    suspend fun insert(answers: Answers)

    @Update
    suspend fun update(answers: Answers)

    @Query("Select * from answer_table WHERE user = :key")
    suspend fun get(key: Long): Answers?

    @Query("Delete from answer_table")
    suspend fun clear()

}