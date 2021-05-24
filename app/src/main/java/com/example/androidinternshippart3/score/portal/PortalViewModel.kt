package com.example.androidinternshippart3.score.portal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import kotlinx.coroutines.launch

class PortalViewModel(application: Application, val userId: Int, val usersDao: UsersDao) : AndroidViewModel(application) {
    private val _buttonResultEvent = SingleLiveEvent<NavDirections>()
    val buttonResultEvent: LiveData<NavDirections> = _buttonResultEvent

    private val _buttonExitEvent = SingleLiveEvent<NavDirections>()
    val buttonExitEvent: LiveData<NavDirections> = _buttonExitEvent

    fun toScoreFragment() {
        viewModelScope.launch {
            _buttonResultEvent.postValue(PortalFragmentDirections.actionPortalFragmentToScoreFragment(userId, false, getUser(userId)))
        }
    }

    fun toUserFragment() {
        _buttonExitEvent.postValue(PortalFragmentDirections.actionPortalFragmentToUserFragment(userId))
    }

    private suspend fun getUser(int: Int): Users {
        return usersDao.get(int.toLong())!!
    }

}