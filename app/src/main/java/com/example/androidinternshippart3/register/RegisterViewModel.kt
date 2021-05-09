package com.example.androidinternshippart3.register

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.CheckEmptyField
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.Tests
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import com.example.androidinternshippart3.roles.Roles
import kotlinx.coroutines.launch


class RegisterViewModel(
    val usersDao: UsersDao,
    val accessDao: AccessDao,
    val testsDao: TestsDao,
    application: Application,
    private val context: FragmentManager
) : AndroidViewModel(application), ShowDialog {

    val model = RegisterModel("", "", "", "", "")
    var dialog: Dialog? = null

    init {
        viewModelScope.launch {
            addTestsToDataBase()
        }
    }


    fun onButtonClick() {
        val checkEmptyField = CheckEmptyField(
            model.firstName,
            model.lastName,
            model.login,
            model.password,
            model.rePassword
        )
        val checkEmpty = checkEmptyField.checkEmptyField()
        val checkPasswords = checkEmptyField.checkEqualPassword()
        showDialogOrRegisterUser(checkEmpty, checkPasswords)
        Log.d("test", "{$model}")
    }

    private fun registerUser() {
        viewModelScope.launch {
            if (getByLogin(model.login) == null) {
                val user = Users()
                val access = Access()

                var role = Roles.USER.role
                if (get(1) == null)
                    role = Roles.ADMINISTRATOR.role
                user.setUser(model.firstName, model.lastName, model.login, model.password, role)
                updateUser(user)
                //todo
                access.setAccessTest1(user.usersId.toInt(),false,false,false)

                insertAccess(access)

                insert(user)
                showDialog(ErrorMessages.SUCCESS.message)
            } else showDialog(ErrorMessages.ALREADY_EXIST.message)
        }
    }


    private suspend fun addTestsToDataBase() {
        if (getTest(1) == null) {
            val firstTest = Tests()
            firstTest.description = " first test"
            insertTest(firstTest)
            val secondTest = Tests()
            secondTest.description = "second test"
            insertTest(secondTest)
            val thirdTest = Tests()
            thirdTest.description = "third test"
            insertTest(thirdTest)
        }

    }

    private suspend fun getByLogin(login: String): Users? {
        return usersDao.getByLogin(login)
    }

    private suspend fun getTest(long: Long): Tests? {
        return testsDao.get(long)
    }
    private suspend fun updateUser(users: Users){
        usersDao.update(users)
    }

    private suspend fun insert(users: Users) {
        usersDao.insert(users)
    }

    private suspend fun insertAccess(access: Access) {
        accessDao.insert(access)
    }

    private suspend fun insertTest(tests: Tests) {
        testsDao.insert(tests)
    }

    private suspend fun clear() {
        usersDao.clear()
    }

    private suspend fun get(long: Long): Users? {
        return usersDao.get(long)
    }

    private fun showDialogOrRegisterUser(checkEmpty: Boolean, checkPasswords: Boolean) {
        if (!checkPasswords && !checkEmpty)
            showDialog(ErrorMessages.PASSWORDS_AND_FIELD.message)
        else if (!checkPasswords)
            showDialog(ErrorMessages.PASSWORDS.message)
        else if (!checkEmpty)
            showDialog(ErrorMessages.FIELDS.message)
        else registerUser()

    }

    override fun showDialog(string: String) {
        dialog = Dialog(string)
        dialog!!.show(context, "")
    }


}