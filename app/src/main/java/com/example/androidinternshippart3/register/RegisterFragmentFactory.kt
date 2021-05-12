package com.example.androidinternshippart3.register

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao

class RegisterFragmentFactory(

    private val usersDao: UsersDao,
    private val accessDao: AccessDao,
    private val testsDao: TestsDao,
    private val questionsDao: QuestionsDao,
    private val answersDao: AnswersDao,
    private val application: Application,
    private val fragmentManager: FragmentManager,
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                usersDao,
                accessDao,
                testsDao,
                questionsDao,
                answersDao,
                application,
                fragmentManager,
                context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}