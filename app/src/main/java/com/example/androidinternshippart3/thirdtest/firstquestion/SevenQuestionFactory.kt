package com.example.androidinternshippart3.thirdtest.firstquestion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.secondtest.thirdquestion.SixQuestionViewModel

class SevenQuestionFactory(val application: Application,
                           val questionsDao: QuestionsDao,
                           val answersDao: AnswersDao,
                           val userId: Int,
                           val resultsDao: ResultsDao
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SevenQuestionViewModel::class.java)) {
            return SevenQuestionViewModel(
                application,
                questionsDao,
                answersDao,
                userId,
                resultsDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}