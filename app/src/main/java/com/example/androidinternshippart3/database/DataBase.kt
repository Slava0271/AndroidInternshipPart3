package com.example.androidinternshippart3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.answers.Answers
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.database.tests.Tests
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao

@Database(
    entities = [Users::class, Access::class, Tests::class, Answers::class, Questions::class, Results::class],
    version = 5, exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract val usersDao: UsersDao
    abstract val accessDao: AccessDao
    abstract val testsDao: TestsDao
    abstract val answersDao: AnswersDao
    abstract val questionsDao: QuestionsDao
    abstract val resultsDao: ResultsDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "data_base"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}