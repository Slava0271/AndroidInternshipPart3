package com.example.androidinternshippart3.usersaccesstest

import android.app.Application
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.roles.Roles
import kotlinx.coroutines.launch


class UsersTestViewModel(
        val usersDao: UsersDao,
        val accessDao: AccessDao,
        val testsDao: TestsDao,
        application: Application,
        val context: FragmentManager,
        val position: Int
) : AndroidViewModel(application) {


    private val _firstCheckBoxEvent = MutableLiveData<Boolean>()
    private val _secondCheckBoxEvent = MutableLiveData<Boolean>()
    private val _thirdCheckBoxEvent = MutableLiveData<Boolean>()
    private val _radioGroupEvent = MutableLiveData<Boolean>()
    private val _navigateToUsersEvent = MutableLiveData<Boolean>()
    private val _managerEvent = MutableLiveData<Boolean>()
    private val _userEvent = MutableLiveData<Boolean>()


    val firstCheckBoxEvent: LiveData<Boolean>
        get() = _firstCheckBoxEvent

    val secondCheckBoxEvent: LiveData<Boolean>
        get() = _secondCheckBoxEvent

    val thirdCheckBoxEvent: LiveData<Boolean>
        get() = _thirdCheckBoxEvent

    val radioGroupEvent: LiveData<Boolean>
        get() = _radioGroupEvent

    val navigateToUsersEvent: LiveData<Boolean>
        get() = _navigateToUsersEvent
    val managerEvent: LiveData<Boolean>
        get() = _managerEvent
    val userEvent: LiveData<Boolean>
        get() = _userEvent


    init {
        _firstCheckBoxEvent.value = true
        _secondCheckBoxEvent.value = true
        _thirdCheckBoxEvent.value = true
        _radioGroupEvent.value = true
        _managerEvent.value = true
        _userEvent.value = true
    }

    fun setEvent() {
        _navigateToUsersEvent.value = true
    }

    fun isClickedOnStart(count: Int, checkBox: CheckBox) {
        viewModelScope.launch {
            val access = getAccess(position.toLong() + 1)

            if (count == 1) checkBox.isChecked = access!!.accessTest1
            else if (count == 2) checkBox.isChecked = access!!.accessTest2
            else if (count == 3) checkBox.isChecked = access!!.accessTest3
        }

    }

    fun getRadioOnStart(radioButtonManager: RadioButton, radioButtonUser: RadioButton) {
        viewModelScope.launch {
            val user = getUser(position.toLong() + 1)
            if (user!!.role == 1) radioButtonManager.isChecked = true
            else if (user.role == 2) radioButtonUser.isChecked = true

        }
    }

    fun onCheckBoxesClicked(isChecked: Boolean, int: Int) {
        viewModelScope.launch {
            val access = getAccess(position.toLong() + 1)
            if (isChecked) {
                if (int == 1)
                    access!!.setAccessTest1(position, true, access.accessTest2, access.accessTest3)
                else if (int == 2)
                    access!!.setAccessTest1(position, access.accessTest1, true, access.accessTest3)
                else if (int == 3)
                    access!!.setAccessTest1(position, access.accessTest1, access.accessTest2, true)
                else throw NullPointerException()
                updateAccess(access)

            } else if (!isChecked) {
                if (int == 1)
                    access!!.setAccessTest1(position, false, access.accessTest2, access.accessTest3)
                else if (int == 2)
                    access!!.setAccessTest1(position, access.accessTest1, false, access.accessTest3)
                else if (int == 3)
                    access!!.setAccessTest1(position, access.accessTest1, access.accessTest2, false)
                else throw NullPointerException()
                updateAccess(access)
            }
        }
    }

    fun listenRadioGroup(checkedId: Int) {
        viewModelScope.launch {
            val user = getUser(position.toLong() + 1)
            when (checkedId) {
                R.id.radioButton -> {
                    user!!.role = Roles.USER.role
                    updateUser(user)
                }
                R.id.radioButton2 -> {
                    user!!.role = Roles.MANAGER.role
                    updateUser(user)
                }
            }
        }
    }


    private suspend fun updateAccess(access: Access) {
        accessDao.update(access)
    }

    private suspend fun updateUser(users: Users) {
        usersDao.update(users)
    }

    private suspend fun getAccess(long: Long): Access? {
        return accessDao.get(long)
    }

    private suspend fun getUser(long: Long): Users? {
        return usersDao.get(long)
    }


}