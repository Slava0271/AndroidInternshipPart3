package com.example.androidinternshippart3.score

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import kotlinx.coroutines.launch

class ScoreViewModel(
        application: Application,
        val resultsDao: ResultsDao,
        val userId: Int,
        val isManager:Boolean
) : AndroidViewModel(application) {
    val scoreModel = ScoreModel("")
    private val _buttonClickEventToUser = SingleLiveEvent<NavDirections>()
    val buttonClickEventToUser: LiveData<NavDirections> = _buttonClickEventToUser

    private val _buttonClickEventToManager = SingleLiveEvent<NavDirections>()
    val buttonClickEventToManager: LiveData<NavDirections> = _buttonClickEventToManager

    init {
        seeScoreAndPercent()
    }

    private fun seeScoreAndPercent() {
        viewModelScope.launch {
            val result = getResult(userId)
            scoreModel.getScore = (result!!.score).toString()
            scoreModel.getTestPassed = result.test.toString()
            //todo +1?
            val percent: Double = (result.score.toDouble()) / (result.question)
            Log.d(
                    "result",
                    result.score.toString() + " " + result.question.toString() + " " + percent.toString()
            )
            if (percent == 1.0)
                scoreModel.getPercentage = "100%"
            else if (percent.toString().length > 3) {
                val roundOffPercent = percent.toString().substring(2, 4) + " %"
                scoreModel.getPercentage = roundOffPercent
            } else if (percent.toString().length <= 3) {
                val roundOffPercent = percent.toString().substring(2, 3) + "0 %"
                scoreModel.getPercentage = roundOffPercent
            }
        }
    }

    fun setButtonEventValue() {
        if(!isManager)
        _buttonClickEventToUser.postValue(ScoreFragmentDirections.actionScoreFragmentToUserFragment(userId))
        else _buttonClickEventToManager.postValue(ScoreFragmentDirections.actionScoreFragmentToManagerFragment())
    }



    private suspend fun getResult(int: Int): Results? {
        return resultsDao.get(int.toLong())
    }

}