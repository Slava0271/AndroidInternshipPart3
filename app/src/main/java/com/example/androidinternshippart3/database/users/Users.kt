package com.example.androidinternshippart3.database.users

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users_table")
data class Users(@PrimaryKey(autoGenerate = true)
                 var usersId: Long = 0L,

                 @ColumnInfo(name = "login")
                 var login: String = "",

                 @ColumnInfo(name = "password")
                 var password: String = "",

                 @ColumnInfo(name = "first_name")
                 var firstName: String = "",

                 @ColumnInfo(name = "last_name")
                 var lastName: String = "",

                 @ColumnInfo(name = "role")
                 var role: Int = 0

) : Parcelable {
    fun setUser(firstName: String, lastName: String, login: String, password: String, role: Int) {
        this.login = login
        this.firstName = firstName
        this.lastName = lastName
        this.password = password
        this.role = role
    }
    fun fullName():String{
        return "$firstName $lastName"
    }
}