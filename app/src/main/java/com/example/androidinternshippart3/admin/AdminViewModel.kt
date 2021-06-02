package com.example.androidinternshippart3.admin

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent

class AdminViewModel(
        application: Application
) : AndroidViewModel(application) {
    private val _navigate = SingleLiveEvent<NavDirections>()
    val navigate: LiveData<NavDirections> = _navigate



    fun setValueToUsers() {
        _navigate.postValue(AdminFragmentDirections.actionAdminFragmentToAdminsUsers())
    }

    fun setValueToFirstTest() {
        _navigate.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(1))
    }

    fun setValueToSecondTest() {
        _navigate.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(2))
    }

    fun setValueToThirdTest() {
        _navigate.postValue(AdminFragmentDirections.actionAdminFragmentToAdminTests(3))
    }

    fun setValueToLogin() {
        _navigate.postValue(AdminFragmentDirections.actionAdminFragmentToLoginFragment())
    }

}