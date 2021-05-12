package com.example.androidinternshippart3.score

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import kotlinx.coroutines.launch

class ScoreViewModel(
    application: Application,
    val resultsDao: ResultsDao,
    val userId: Int
) : AndroidViewModel(application) {
    val scoreModel = ScoreModel("")
    private val _buttonClickEvent = MutableLiveData<Boolean>()

    val buttonClickEvent: LiveData<Boolean>
        get() = _buttonClickEvent

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
        _buttonClickEvent.value = true
    }

    private suspend fun getResult(int: Int): Results? {
        return resultsDao.get(int.toLong())
    }

}