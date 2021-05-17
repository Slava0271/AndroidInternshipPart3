package com.example.androidinternshippart3.database.tests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_table")
data class Tests(
    @PrimaryKey(autoGenerate = true)
    var testId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "description")
    var description: String = ""
) {
    fun setTest(name: String, description: String) {
        this.name = name
        this.description = description
    }
}

