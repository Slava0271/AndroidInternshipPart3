package com.example.androidinternshippart3.score.portal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.score.ScoreViewModel

class PortalFragmentFactory(
        private val application: Application,
        private val userId: Int,
        private val usersDao: UsersDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortalViewModel::class.java)) {
            return PortalViewModel(
                    application, userId,usersDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}