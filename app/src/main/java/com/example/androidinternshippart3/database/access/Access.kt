package com.example.androidinternshippart3.database.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_table")
data class Access(@PrimaryKey(autoGenerate = true)
                  var usersId: Long = 0L,

                  @ColumnInfo(name = "user")
                  var user: Int,

                  @ColumnInfo(name = "test")
                  var test: Int,

                  @ColumnInfo(name = "access")
                  var access: Boolean
)