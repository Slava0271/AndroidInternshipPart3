package com.example.androidinternshippart3.firsttest.secondquestion

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

class SecondQuestionViewModel(
        application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao
) : AndroidViewModel(application) {
    val secondQuestionModel = SecondQuestionModel("")

    private val _setImageEvent = MutableLiveData<Boolean>()

    private val _firstButtonEvent = SingleLiveEvent<NavDirections>()
    private val _secondButtonEvent = SingleLiveEvent<NavDirections>()
    private val _thirdButtonEvent = SingleLiveEvent<NavDirections>()
    private val _navigateBackEvent = SingleLiveEvent<NavDirections>()


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<NavDirections>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<NavDirections>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<NavDirections>
        get() = _thirdButtonEvent
    val navigateBackEvent: LiveData<NavDirections>
        get() = _navigateBackEvent

    init {
        getQuestionText()
        _setImageEvent.value = true
    }

    fun eventBack() {
        _navigateBackEvent.postValue(SecondQuestionDirections.actionSecondQuestionToUserFragment(userId))
    }

    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            secondQuestionModel.secondQuestionText = questions!!.text
        }
    }

    fun secondQuestion(boolean: Boolean) {
        viewModelScope.launch {
            val results = getResult(userId)
            if (boolean)
                results!!.score += 1F
            results!!.question += 1
            updateResult(results!!)
        }
    }

    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(SecondQuestionDirections.actionSecondQuestionToThirdQuestionFragment(userId))
    }


    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(SecondQuestionDirections.actionSecondQuestionToThirdQuestionFragment(userId))
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(SecondQuestionDirections.actionSecondQuestionToThirdQuestionFragment(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(2)
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