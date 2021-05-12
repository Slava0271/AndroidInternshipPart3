package com.example.androidinternshippart3.firsttest.secondquestion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
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

    private val _firstButtonEvent = MutableLiveData<Boolean>()
    private val _secondButtonEvent = MutableLiveData<Boolean>()
    private val _thirdButtonEvent = MutableLiveData<Boolean>()


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<Boolean>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<Boolean>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<Boolean>
        get() = _thirdButtonEvent

    init {
        getQuestionText()
        _setImageEvent.value = true
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
        _firstButtonEvent.value = true
    }


    fun setSecondButtonEvent() {
        _secondButtonEvent.value = true
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.value = true
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