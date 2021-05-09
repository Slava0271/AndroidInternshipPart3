package com.example.androidinternshippart3.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToLoginEvent = MutableLiveData<Boolean>()

    val navigateToLoginEvent: LiveData<Boolean>
        get() = _navigateToLoginEvent

    fun setEventValue() {
        _navigateToLoginEvent.value = true
    }

}