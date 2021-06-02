package com.example.androidinternshippart3.login

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.users.UsersDao

class LoginFragmentFactory(
    private val usersDao: UsersDao,
    private val application: Application,
    private val supportFragmentManager: FragmentManager
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                usersDao,
                application,
                supportFragmentManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}