package com.example.androidinternshippart3.user

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import kotlinx.coroutines.launch

class UserViewModel(
    val accessDao: AccessDao,
    application: Application,
    val id: Int,
    val fragmentManager: FragmentManager,
    val usersDao: UsersDao,
    val resultsDao: ResultsDao
) :
    AndroidViewModel(application), ShowDialog {
    val userModel = UserModel("")

    private val _navigateToLoginEvent = MutableLiveData<Boolean>()
    private val _navigateToFirstTest = MutableLiveData<Boolean>()
    private val _navigateToSecondTest = MutableLiveData<Boolean>()
    private val _navigateToThirdTest = MutableLiveData<Boolean>()
    private val _navigateToResults = MutableLiveData<Boolean>()


    val navigateToLoginEvent: LiveData<Boolean>
        get() = _navigateToLoginEvent

    val navigateToFirstTest: LiveData<Boolean>
        get() = _navigateToFirstTest

    val navigateToSecondTest: LiveData<Boolean>
        get() = _navigateToSecondTest

    val navigateToThirdTest: LiveData<Boolean>
        get() = _navigateToThirdTest
    val navigateToResults: LiveData<Boolean>
        get() = _navigateToResults

    init {
        setHelloText()
    }

    fun setEventValue() {
        _navigateToLoginEvent.value = true
    }

    fun toFirstTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest1)
                _navigateToFirstTest.value = true
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    private fun setHelloText() {
        viewModelScope.launch {
            val user = getUser(id)
            Log.d("user", user!!.firstName)
            userModel.helloUser = "Hello, " + user.firstName
        }
    }

    fun toSecondTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest2)
                _navigateToSecondTest.value = true
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    fun toThirdTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest3)
                _navigateToThirdTest.value = true
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    fun navigateToResults() {
        viewModelScope.launch {
            if (getResult(id) != null)
                _navigateToResults.value = true
            else showDialog(ErrorMessages.NO_TEST_PASSED.message)
        }
    }

    private suspend fun getAccess(long: Int): Access? {
        return accessDao.get(long.toLong())
    }

    private suspend fun getUser(long: Int): Users? {
        return usersDao.get(long.toLong())
    }

    private suspend fun getResult(long: Int): Results? {
        return resultsDao.get(long.toLong())
    }

    override fun showDialog(string: String) {
        val dialog = Dialog(string)
        dialog.show(fragmentManager, "")
    }


}