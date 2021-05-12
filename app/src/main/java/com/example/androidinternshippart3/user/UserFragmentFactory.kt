package com.example.androidinternshippart3.user

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.login.LoginViewModel

class UserFragmentFactory(
    private val application: Application,
    private val accessDao: AccessDao,
    private val id: Int,
    private val fragmentManager: FragmentManager,
    private val usersDao: UsersDao,
    private val resultsDao: ResultsDao
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                accessDao,
                application,
                id,
                fragmentManager,
                usersDao,
                resultsDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}