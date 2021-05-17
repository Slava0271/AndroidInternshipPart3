package com.example.androidinternshippart3.register

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao

class RegisterFragmentFactory(

    private val usersDao: UsersDao,
    private val accessDao: AccessDao,
    private val testsDao: TestsDao,
    private val application: Application,
    private val context: FragmentManager
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(usersDao, accessDao, testsDao, application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}