package com.example.androidinternshippart3.secondtest.thirdquestion

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidinternshippart3.database.answers.Answers
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.secondtest.secondquestion.FifthQuestionModel
import kotlinx.coroutines.launch

class SixQuestionViewModel(
    application: Application,
    val questionsDao: QuestionsDao,
    val answersDao: AnswersDao,
    val userId: Int,
    val resultsDao: ResultsDao
):AndroidViewModel(application) {
    private val _setImageEvent = MutableLiveData<Boolean>()
    private val _firstButtonEvent = MutableLiveData<Boolean>()
    private val _secondButtonEvent = MutableLiveData<Boolean>()
    private val _thirdButtonEvent = MutableLiveData<Boolean>()

    val sixModel = SixQuestionModel("")

    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<Boolean>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<Boolean>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<Boolean>
        get() = _thirdButtonEvent

    init {
        _setImageEvent.value = true
        getQuestionText()
    }

    fun sixQuestion(boolean: Boolean) {
        Log.d("results", "123")
        viewModelScope.launch {
            val results = getResult(userId)
            if (boolean)
                results!!.score += 1F
            results!!.question += 1
            updateResult(results!!)
        }

    }

    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            sixModel.sixQuestion = questions!!.text.substring(0, questions.text.length - 1)

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
        return questionsDao.get(6)
    }

    private suspend fun getAnswer(long: Int): Answers? {
        return answersDao.get(long.toLong())
    }

    private suspend fun insertAnswer(answers: Answers) {
        answersDao.insert(answers)
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