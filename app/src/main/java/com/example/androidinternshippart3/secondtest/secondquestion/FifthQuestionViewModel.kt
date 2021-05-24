package com.example.androidinternshippart3.secondtest.secondquestion

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
import kotlinx.coroutines.launch

class FifthQuestionViewModel(
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
    private val _navigateBack = SingleLiveEvent<NavDirections>()

    val fifthModel = FifthQuestionModel("")


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<NavDirections>
        get() = _firstButtonEvent
    val secondButtonEvent: LiveData<NavDirections>
        get() = _secondButtonEvent
    val thirdButtonEvent: LiveData<NavDirections>
        get() = _thirdButtonEvent
    val navigateBack: LiveData<NavDirections>
        get() = _navigateBack

    init {
        _setImageEvent.value = true
        getQuestionText()
    }

    fun fifthQuestion(boolean: Boolean) {
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
            fifthModel.fifthQuestion = questions!!.text
        }
    }

    fun eventBack(){
        _navigateBack.postValue(FifthQuestionFragmentDirections.actionFifthQuestionFragmentToUserFragment(userId))
    }
    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(FifthQuestionFragmentDirections.actionFifthQuestionFragmentToSixQuestionFragment(userId))
    }

    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(FifthQuestionFragmentDirections.actionFifthQuestionFragmentToSixQuestionFragment(userId))
    }

    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(FifthQuestionFragmentDirections.actionFifthQuestionFragmentToSixQuestionFragment(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(5)
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