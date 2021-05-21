package com.example.androidinternshippart3.firsttest.firstquestion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.database.answers.Answers
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.results.Results
import com.example.androidinternshippart3.database.results.ResultsDao
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstQuestionViewModel(
        application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao
) : AndroidViewModel(application) {
    private val _setImageEvent = MutableLiveData<Boolean>()
    private val _firstButtonEvent = SingleLiveEvent<NavDirections>()
    private val _secondButtonEvent = SingleLiveEvent<NavDirections>()
    private val _thirdButtonEvent = SingleLiveEvent<NavDirections>()

    val firstQuestionModel = FirstQuestionModel("")


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<NavDirections>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<NavDirections>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<NavDirections>
        get() = _thirdButtonEvent

    init {
        _setImageEvent.value = true
        getQuestionText()
        setButtonText()
    }

    fun firstQuestion(boolean: Boolean) {
        viewModelScope.launch {
            if (getResult(userId) == null) {
                val results = Results()
                results.user = userId
                if (boolean)
                    results.score += 1F
                results.question += 1
                results.test += 1
                insertResult(results)
            } else {
                val results = getResult(userId)
                if (boolean)
                    results!!.score += 1F
                results!!.question += 1
                results!!.test += 1
                updateResult(results!!)
            }
        }
    }

    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            firstQuestionModel.question = questions!!.text
        }
    }

    private fun setButtonText() {
        GlobalScope.launch {
            val answers = getAnswers()
            firstQuestionModel.firstAnswer = answers[0].text
            firstQuestionModel.secondAnswer = answers[1].text
            firstQuestionModel.thirdAnswer = answers[2].text
        }
    }

    private suspend fun getAnswers(): ArrayList<Answers> {
        val list = getAnswer()
        val rightAnswers = ArrayList<Answers>()
        for (i in list.indices) {
            if (list[i].question == 1)
                rightAnswers.add(list[i])
        }

        return rightAnswers
    }

    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(FirstQuestionDirections.actionFirstQuestionToSecondQuestion(userId))
    }

    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(FirstQuestionDirections.actionFirstQuestionToSecondQuestion(userId))
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(FirstQuestionDirections.actionFirstQuestionToSecondQuestion(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(1)
    }

    private suspend fun getAnswer(): List<Answers> {
        return answersDao.getAllAnswers()
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