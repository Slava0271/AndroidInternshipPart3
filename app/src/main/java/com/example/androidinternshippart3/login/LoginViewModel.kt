package com.example.androidinternshippart3.login

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import com.example.androidinternshippart3.roles.Roles
import kotlinx.coroutines.launch

class LoginViewModel(
        val usersDao: UsersDao,
        accessDao: AccessDao,
        application: Application,
        val supportFragmentManager: FragmentManager
) : AndroidViewModel(application), ShowDialog {

    private val _navigationEventToAdmin = SingleLiveEvent<NavDirections>()
    val navigationEventToAdmin: LiveData<NavDirections> = _navigationEventToAdmin

    private val _navigateEventToUser = SingleLiveEvent<NavDirections>()
    val navigateEventToUser: LiveData<NavDirections> = _navigateEventToUser


    private val _navigateEventManager = SingleLiveEvent<NavDirections>()
    val navigateEventManager: LiveData<NavDirections> = _navigateEventManager

    val loginModel = LoginModel()
    var dialog: Dialog? = null

    var userId = 0


    fun getFields() {
        //Log.d("test", loginModel.login2 + " " + loginModel.password2)
        viewModelScope.launch {
            val user = getByLogin(loginModel.login2)
            userId = user!!.usersId.toInt()

            if (loginModel.password2 == user.password) {
            //    Log.d("User", user.toString())
                when (user.role) {
                    Roles.ADMINISTRATOR.role -> _navigationEventToAdmin.postValue(LoginFragmentDirections.actionLoginFragmentToAdminFragment(5))
                    Roles.USER.role -> _navigateEventToUser.postValue(LoginFragmentDirections.actionLoginFragmentToUserFragment(userId))
                    Roles.MANAGER.role -> _navigateEventManager.postValue(LoginFragmentDirections.actionLoginFragmentToManagerFragment())
                }
            } else showDialog(ErrorMessages.USER_NOT_EXIST.message)
        }
    }


    private suspend fun getByLogin(login: String): Users? {
        return usersDao.getByLogin(login)
    }

    override fun showDialog(string: String) {
        dialog = Dialog(string)
        dialog!!.show(supportFragmentManager, "")
    }
}