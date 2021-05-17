package com.example.androidinternshippart3.login

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.admin.AdminFragment
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import kotlinx.coroutines.launch

class LoginViewModel(
    val usersDao: UsersDao,
    accessDao: AccessDao,
    application: Application,
    val supportFragmentManager: FragmentManager
) : AndroidViewModel(application), ShowDialog {

    val loginModel = LoginModel()
    var dialog: Dialog? = null


    fun getFields() {
        Log.d("test", loginModel.login2 + " " + loginModel.password2)
        viewModelScope.launch {
            val user = getByLogin(loginModel.login2)
            if (user != null && loginModel.password2 == user.password) {
                Log.d("User", user.toString())
                val fragment: Fragment = AdminFragment()
                changeFragment(fragment)
            } else showDialog(ErrorMessages.USER_NOT_EXIST.message)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        supportFragmentManager.executePendingTransactions();
        fragmentTransaction.commit()
    }

    private suspend fun getByLogin(login: String): Users? {
        return usersDao.getByLogin(login)
    }

    override fun showDialog(string: String) {
        dialog = Dialog(string)
        dialog!!.show(supportFragmentManager, "")
    }
}