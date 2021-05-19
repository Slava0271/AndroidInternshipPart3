package com.example.androidinternshippart3.admin

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent

class AdminViewModel(
        val usersDao: UsersDao,
        val accessDao: AccessDao,
        val testsDao: TestsDao,
        application: Application,
        val context: Context,
        val supportFragmentManager: FragmentManager


) : AndroidViewModel(application) {
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _navigationEventBack = SingleLiveEvent<NavDirections>()
    val navigationEventBack: LiveData<NavDirections> = _navigationEventBack

    private val _navigationEventToTests = SingleLiveEvent<NavDirections>()
    val navigationEventToTests: LiveData<NavDirections> = _navigationEventToTests


    fun setValueToUsers() {
        _navigationEvent.postValue(AdminFragmentDirections.actionAdminFragmentToAdminsUsers())
    }

    fun setValueToFirstTest() {
        _navigationEventToTests.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(1))
    }

    fun setValueToSecondTest() {
        _navigationEventToTests.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(2))
    }

    fun setValueToThirdTest() {
        _navigationEventToTests.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(3))
    }

    fun setValueToLogin() {
        _navigationEventBack.postValue(AdminFragmentDirections.actionAdminFragmentToLoginFragment())
    }

}