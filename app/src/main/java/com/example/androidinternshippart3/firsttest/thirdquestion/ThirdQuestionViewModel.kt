package com.example.androidinternshippart3.firsttest.thirdquestion

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
        writeAnswers()
    }

    fun eventBack() {
        _navigateBackEvent.postValue(ThirdQuestionFragmentDirections.actionThirdQuestionFragmentToUserFragment(userId))
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

    private fun writeAnswers() {
        GlobalScope.launch() {
            val answers = getRightAnswers()
            thirdQuestionModel.firstAnswerQuestionThree = answers[0].text
            thirdQuestionModel.secondAnswerQuestionThree = answers[1].text
            thirdQuestionModel.thirdAnswerQuestionThree = answers[2].text
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


    private suspend fun getRightAnswers(): ArrayList<Answers> {
        val list = getAllAnswerForQuestion()
        val rightAnswers = ArrayList<Answers>()
        for (i in list.indices) {
            if (list[i].question == 3)
                rightAnswers.add(list[i])
        }

        return rightAnswers
    }

    private suspend fun getAllAnswerForQuestion(): List<Answers> {
        return answersDao.getAllAnswers()
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