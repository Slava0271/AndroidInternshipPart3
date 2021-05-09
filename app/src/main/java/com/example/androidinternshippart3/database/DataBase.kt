package com.example.androidinternshippart3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.Tests
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao

@Database(entities = [Users::class, Access::class, Tests::class], version = 3, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract val usersDao: UsersDao
    abstract val accessDao: AccessDao
    abstract val testsDao: TestsDao

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