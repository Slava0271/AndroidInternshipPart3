package com.example.androidinternshippart3.manager

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManagerViewModel(private val database: DataBase) : ViewModel() {
    private val _navigate = SingleLiveEvent<NavDirections>()
    val navigate: LiveData<NavDirections> = _navigate

    private val _users = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>> = _users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _users.postValue(database.usersDao.getAllUsers())
        }
    }

    fun onClickBack(){
        _navigate.postValue(ManagerFragmentDirections.actionManagerFragmentToLoginFragment())
    }
}