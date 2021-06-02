package com.example.androidinternshippart3.score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.register.RegisterViewModel

class ScoreFragmentFactory(
        private val application: Application,
        private val resultsDao: ResultsDao,
        private val scoreId: Int,
        private val isManager: Boolean
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(
                    application,
                    resultsDao,
                    scoreId,
                    isManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
