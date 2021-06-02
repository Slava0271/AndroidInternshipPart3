package com.example.androidinternshippart3.database.answers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer_table")
data class Answers(
    @PrimaryKey(autoGenerate = true)
    var answersId: Long = 0L,

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "score")
    var score: Float = 0F,

    @ColumnInfo(name = "question")
    var question: Int = 0,

    @ColumnInfo(name = "user")
    var user: Int = 0
) {

    fun setAnswer(text: String, score: Float, question: Int, user: Int) {
        this.text = text
        this.score = score
        this.question = question
        this.user = user
    }
}