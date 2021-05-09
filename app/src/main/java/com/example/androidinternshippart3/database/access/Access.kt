package com.example.androidinternshippart3.database.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_table")
data class Access(

        @PrimaryKey(autoGenerate = true)
        var accessId: Long = 0L,

        @ColumnInfo(name = "user")
        var user: Int = 0,

        @ColumnInfo(name = "accessTest1")
        var accessTest1: Boolean = false,

        @ColumnInfo(name = "accessTest2")
        var accessTest2: Boolean = false,

        @ColumnInfo(name = "accessTest3")
        var accessTest3: Boolean = false


) {
    fun setAccessTest1(user: Int, accessTest1: Boolean,accessTest2: Boolean,accessTest3: Boolean) {
        this.user = user
        this.accessTest1 = accessTest1
        this.accessTest2 = accessTest2
        this.accessTest3 = accessTest3


    }

    fun setAccessTest2(user: Int, accessTest2: Boolean) {
        this.user = user
        this.accessTest2 = accessTest2
    }

    fun setAccessTest3(user: Int, accessTest3: Boolean) {
        this.user = user
        this.accessTest3 = accessTest3
    }
}