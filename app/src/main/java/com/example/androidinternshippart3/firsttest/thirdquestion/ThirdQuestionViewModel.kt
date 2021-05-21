package com.example.androidinternshippart3.firsttest.thirdquestion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import kotlinx.coroutines.launch

class ThirdQuestionViewModel(
        application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao
) : AndroidViewModel(application) {
    val thirdQuestionModel = ThirdQuestionModel("")

    private val _setImageEvent = MutableLiveData<Boolean>()


    private val _firstButtonEvent = SingleLiveEvent<NavDirections>()
    private val _secondButtonEvent = SingleLiveEvent<NavDirections>()
    private val _thirdButtonEvent = SingleLiveEvent<NavDirections>()


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent

    val firstButtonEvent: LiveData<NavDirections>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<NavDirections>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<NavDirections>
        get() = _thirdButtonEvent

    init {
        getQuestionText()
        _setImageEvent.value = true
    }

    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            thirdQuestionModel.thirdQuestionText =
                    questions!!.text.substring(0, questions.text.length - 1)
            // str = str.substring(0,str.length-1)
            //thirdQuestionModel.thirdQuestionText = str
        }
    }

    fun thirdQuestion(boolean: Boolean) {
        viewModelScope.launch {
            val results = getResult(userId)
            if (boolean)
                results!!.score += 1F
            results!!.question += 1
            updateResult(results!!)
        }
    }

    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(ThirdQuestionFragmentDirections.actionThirdQuestionFragmentToPortalFragment(userId))
    }

    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(ThirdQuestionFragmentDirections.actionThirdQuestionFragmentToPortalFragment(userId))
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(ThirdQuestionFragmentDirections.actionThirdQuestionFragmentToPortalFragment(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(3)
    }

    private suspend fun getResult(int: Int): Results? {
        return resultsDao.get(int.toLong())
    }

    private suspend fun insertResult(results: Results) {
        resultsDao.insert(results)
    }

    private suspend fun updateResult(results: Results) {
        resultsDao.update(results)
    }
}