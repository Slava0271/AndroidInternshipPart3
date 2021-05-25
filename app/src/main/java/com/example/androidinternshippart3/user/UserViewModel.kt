package com.example.androidinternshippart3.user

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
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

    private val _navigateToLoginEvent = SingleLiveEvent<NavDirections>()
    val navigationToLoginEvent: LiveData<NavDirections> = _navigateToLoginEvent

    private val _navigationEventToFirstTest = SingleLiveEvent<NavDirections>()
    val navigationEventToFirstTest: LiveData<NavDirections> = _navigationEventToFirstTest

    private val _navigateToResults = SingleLiveEvent<NavDirections>()
    val navigateToResults: LiveData<NavDirections> = _navigateToResults


    private val _navigateToSecondTest = SingleLiveEvent<NavDirections>()
    val navigateToSecondTest: LiveData<NavDirections> = _navigateToSecondTest


    private val _navigateToThirdTest = SingleLiveEvent<NavDirections>()
    val navigateToThirdTest: LiveData<NavDirections> = _navigateToThirdTest


    init {
        setHelloText()
    }

    fun setEventValue() {
        _navigateToLoginEvent.postValue(UserFragmentDirections.actionUserFragmentToLoginFragment())
    }

    fun toFirstTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest1)
                _navigationEventToFirstTest.postValue(UserFragmentDirections.actionUserFragmentToFirstQuestion(id))
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    private fun setHelloText() {
        viewModelScope.launch {
            val user = getUser(id)
            Log.d("user", user!!.firstName + " " + id.toString())
            userModel.helloUser = "Hello, " + user.firstName
        }
    }

    fun toSecondTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest2)
                _navigateToSecondTest.postValue(UserFragmentDirections.actionUserFragmentToFourhQuestionFragment(id))
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    fun toThirdTest() {
        viewModelScope.launch {
            if (getAccess(id)!!.accessTest3)
                _navigateToThirdTest.postValue(UserFragmentDirections.actionUserFragmentToSevenQuestionFragment(id))
            else showDialog(ErrorMessages.TEST_NO_ACCESS.message)
        }
    }

    fun navigateToResults() {
        viewModelScope.launch {
            if (getResult(id) != null)
                _navigateToResults.postValue(getUser(id)?.let { UserFragmentDirections.actionUserFragmentToScoreFragment(id,false, it) })
            else showDialog(ErrorMessages.NO_TEST_PASSED.message)
        }
    }

    private suspend fun getAccess(long: Int): Access? {
        return accessDao.get(long.toLong())
    }

    private suspend fun getUser(int: Int): Users {
        return usersDao.getAllUsers()[int-1]
    }

    private suspend fun getResult(long: Int): Results? {
        return resultsDao.get(long.toLong())
    }

    override fun showDialog(string: String) {
        val dialog = Dialog(string)
        dialog.show(fragmentManager, "")
    }


}