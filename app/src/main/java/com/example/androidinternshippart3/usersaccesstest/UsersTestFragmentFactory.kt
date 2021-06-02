package com.example.androidinternshippart3.usersaccesstest

import android.app.Application
import android.widget.CheckBox
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.register.RegisterViewModel

class UsersTestFragmentFactory(
        val usersDao: UsersDao,
        val accessDao: AccessDao,
        val testsDao: TestsDao,
        val application: Application,
        val context: FragmentManager,
        val position: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersTestViewModel::class.java)) {
            return UsersTestViewModel(
                    usersDao, accessDao, testsDao, application, context, position
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}