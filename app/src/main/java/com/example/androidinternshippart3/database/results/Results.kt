package com.example.androidinternshippart3.database.results

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results_table")
data class Results(
    @PrimaryKey(autoGenerate = true)
    var resultsId: Long = 0L,

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "score")
    var score: Float = 0F,

    @ColumnInfo(name = "question")
    var question: Int = 0,

    @ColumnInfo(name = "user")
    var user: Int = 0,

    @ColumnInfo(name = "test")
    var test: Int = 0
) {

    fun setResults(date: String, score: Float, question: Int, user: Int, test: Int) {
        this.date = date
        this.score = score
        this.question = question
        this.user = user
        this.test = test
    }
}