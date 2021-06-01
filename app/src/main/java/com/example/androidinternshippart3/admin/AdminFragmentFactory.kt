package com.example.androidinternshippart3.admin

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao

class AdminFragmentFactory(
        val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(
                    application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}