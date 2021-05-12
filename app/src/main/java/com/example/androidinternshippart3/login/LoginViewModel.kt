package com.example.androidinternshippart3.login

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import com.example.androidinternshippart3.roles.Roles
import kotlinx.coroutines.launch

class LoginViewModel(
    val usersDao: UsersDao,
    accessDao: AccessDao,
    application: Application,
    val supportFragmentManager: FragmentManager
) : AndroidViewModel(application), ShowDialog {

    private val _navigateEventToAdmin = MutableLiveData<Boolean>()
    private val _navigateEventToUser = MutableLiveData<Boolean>()
    private val _navigateEventManager = MutableLiveData<Boolean>()


    val navigateEventToAdmin: LiveData<Boolean>
        get() = _navigateEventToAdmin
    val navigateEventToUser: LiveData<Boolean>
        get() = _navigateEventToUser
    val navigateEventManager: LiveData<Boolean>
        get() = _navigateEventManager

    val loginModel = LoginModel()
    var dialog: Dialog? = null

    var userId = 0


    fun getFields() {
        Log.d("test", loginModel.login2 + " " + loginModel.password2)
        viewModelScope.launch {
            val user = getByLogin(loginModel.login2)
            if (user != null && loginModel.password2 == user.password) {
                Log.d("User", user.toString())
                if (user.role == Roles.ADMINISTRATOR.role)
                    _navigateEventToAdmin.value = true
                else if (user.role == Roles.USER.role) {
                    userId = user.usersId.toInt()
                    _navigateEventToUser.value = true
                } else if (user.role == Roles.MANAGER.role) {
                    _navigateEventManager.value = true
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