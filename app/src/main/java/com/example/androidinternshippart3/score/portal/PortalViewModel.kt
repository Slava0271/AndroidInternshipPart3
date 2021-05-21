package com.example.androidinternshippart3.score.portal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent

class PortalViewModel(application: Application, val userId: Int) : AndroidViewModel(application) {
    private val _buttonResultEvent = SingleLiveEvent<NavDirections>()
    val buttonResultEvent: LiveData<NavDirections> = _buttonResultEvent

    private val _buttonExitEvent = SingleLiveEvent<NavDirections>()
    val buttonExitEvent: LiveData<NavDirections> = _buttonExitEvent

    fun toScoreFragment(){
        _buttonResultEvent.postValue(PortalFragmentDirections.actionPortalFragmentToScoreFragment(userId))
    }
    fun toUserFragment(){
        _buttonExitEvent.postValue(PortalFragmentDirections.actionPortalFragmentToUserFragment(userId))
    }

}