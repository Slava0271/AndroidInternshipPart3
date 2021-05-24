package com.example.androidinternshippart3.thirdtest.secondquestion

import android.app.Application
import android.util.Log
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

class EighthWQuestionViewModel(
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
    private val _navigateBackEvent = SingleLiveEvent<NavDirections>()

    val eighthModel = EighthQuestionModel("")

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
        writeAnswers()
        _setImageEvent.value = true
        getQuestionText()
    }

    fun eventBack(){
        _navigateBackEvent.postValue(EighthQuestionFragmentDirections.actionEighthQuestionFragmentToUserFragment(userId))
    }
    fun eighthQuestion(boolean: Boolean) {
        Log.d("results", "123")
        viewModelScope.launch {
            val results = getResult(userId)
            if (boolean)
                results!!.score += 1F
            results!!.question += 1
            updateResult(results!!)
        }

    }
    private fun writeAnswers() {
        GlobalScope.launch() {
            val answers = getRightAnswers()
            eighthModel.eighthQuestionFirstAnswer = answers[0].text
            eighthModel.eighthQuestionSecondAnswer = answers[1].text
            eighthModel.eighthQuestionThirdAnswer = answers[2].text
        }
    }

    private suspend fun getRightAnswers(): ArrayList<Answers> {
        val list = getAllAnswerForQuestion()
        val rightAnswers = ArrayList<Answers>()
        for (i in list.indices) {
            if (list[i].question == 8)
                rightAnswers.add(list[i])
        }

        return rightAnswers
    }

    private suspend fun getAllAnswerForQuestion(): List<Answers> {
        return answersDao.getAllAnswers()
    }


    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            eighthModel.eighthQuestion = questions!!.text
        }
    }

    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(EighthQuestionFragmentDirections.actionEighthQuestionFragmentToNineQuestionFragment(userId))
    }


    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(EighthQuestionFragmentDirections.actionEighthQuestionFragmentToNineQuestionFragment(userId))
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(EighthQuestionFragmentDirections.actionEighthQuestionFragmentToNineQuestionFragment(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(8)
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