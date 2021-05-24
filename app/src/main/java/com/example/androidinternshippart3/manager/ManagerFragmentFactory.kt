package com.example.androidinternshippart3.manager

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.register.RegisterViewModel

class ManagerFragmentFactory(
        private val database: DataBase) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagerViewModel::class.java)) {
            return ManagerViewModel(
                    database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}