package com.example.androidinternshippart3.thirdtest.firstquestion

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
import com.example.androidinternshippart3.secondtest.thirdquestion.SixQuestionModel
import kotlinx.coroutines.launch

class SevenQuestionViewModel(
        application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao
) : AndroidViewModel(application) {
    private val _setImageEvent = MutableLiveData<Boolean>()
    private val _firstButtonEvent = SingleLiveEvent<NavDirections>()
    private val _secondButtonEvent = SingleLiveEvent<NavDirections>()
    private val _thirdButtonEvent = MutableLiveData<NavDirections>()

    val sevenModel = SevenQuestionModel("")

    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val firstButtonEvent: LiveData<NavDirections> = _firstButtonEvent
    val secondButtonEvent: LiveData<NavDirections> = _secondButtonEvent
    val thirdButtonEvent: LiveData<NavDirections> = _thirdButtonEvent

    init {
        _setImageEvent.value = true
        getQuestionText()
    }

    fun sevenQuestion(boolean: Boolean) {
        Log.d("results", "123")
        viewModelScope.launch {
            if (getResult(userId) == null) {
                val results = Results()
                results.user = userId
                if (boolean)
                    results.score += 1F
                results.question += 1
                results.test += 1
                Log.d("results", results.question.toString())
                insertResult(results)
            } else {
                val results = getResult(userId)
                if (boolean)
                    results!!.score += 1F
                results!!.test += 1
                results!!.question += 1
                updateResult(results!!)
            }
        }
    }


    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            sevenModel.sevenQuestion = questions!!.text

        }
    }

    fun setFirstButtonEvent() {
        _firstButtonEvent.postValue(SevenQuestionFragmentDirections.actionSevenQuestionFragmentToEighthQuestionFragment(userId))
    }
    fun setSecondButtonEvent() {
        _secondButtonEvent.postValue(SevenQuestionFragmentDirections.actionSevenQuestionFragmentToEighthQuestionFragment(userId))
    }
    fun setThirdButtonEvent() {
        _thirdButtonEvent.postValue(SevenQuestionFragmentDirections.actionSevenQuestionFragmentToEighthQuestionFragment(userId))
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(7)
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