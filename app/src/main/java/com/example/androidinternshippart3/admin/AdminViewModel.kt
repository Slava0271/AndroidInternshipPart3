package com.example.androidinternshippart3.admin

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.admin.users.AdminsUsersDirections
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

    @Deprecated("Using a different navigation method")
    private val _navigateEventToUsers = MutableLiveData<Boolean>()

    private val _navigateEventToFirstTest = MutableLiveData<Boolean>()
    private val _navigateEventToSecondTest = MutableLiveData<Boolean>()
    private val _navigateEventToThirdTest = MutableLiveData<Boolean>()
    private val _navigateEventToLogin = MutableLiveData<Boolean>()

    val navigateEventToUsers: LiveData<Boolean>
        get() = _navigateEventToUsers

    val navigateEventToFirstTest: LiveData<Boolean>
        get() = _navigateEventToFirstTest
    val navigateEventToSecondTest: LiveData<Boolean>
        get() = _navigateEventToSecondTest
    val navigateEventToThirdTest: LiveData<Boolean>
        get() = _navigateEventToThirdTest
    val navigateEventToLogin: LiveData<Boolean>
        get() = _navigateEventToLogin

    fun setValueToUsers() {
        _navigationEvent.postValue(AdminFragmentDirections.actionAdminFragmentToAdminsUsers())
    }

    fun setValueToFirstTest() {
        _navigateEventToFirstTest.value = true
    }

    fun setValueToLogin() {
        _navigateEventToLogin.value = true
    }

    fun setValueToSecondTest() {
        _navigateEventToSecondTest.value = true
    }

    fun setValueToThirdTest() {
        _navigateEventToThirdTest.value = true
    }

}