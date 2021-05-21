package com.example.androidinternshippart3.score.portal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.score.ScoreViewModel

class PortalFragmentFactory(
        private val application: Application,
        private val userId: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortalViewModel::class.java)) {
            return PortalViewModel(
                    application, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}