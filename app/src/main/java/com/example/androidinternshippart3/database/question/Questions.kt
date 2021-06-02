package com.example.androidinternshippart3.database.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Questions(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0L,

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "type")
    var type: Int = 0,

    @ColumnInfo(name = "test")
    var test: Int = 0
) {
    fun setQuestion(text: String, type: Int, test: Int) {
        this.text = text
        this.type = type
        this.test = test
    }
}