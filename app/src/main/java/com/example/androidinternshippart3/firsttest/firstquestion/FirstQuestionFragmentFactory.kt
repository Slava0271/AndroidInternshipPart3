package com.example.androidinternshippart3.firsttest.firstquestion

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.register.RegisterViewModel

class FirstQuestionFragmentFactory
(
        val application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao,
        val question: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstQuestionViewModel::class.java)) {
            return FirstQuestionViewModel(
                    application,
                    questionsDao,
                    answersDao,
                    userId,
                    resultsDao,
                    question
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}