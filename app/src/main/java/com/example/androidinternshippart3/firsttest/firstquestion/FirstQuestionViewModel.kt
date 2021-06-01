package com.example.androidinternshippart3.firsttest.firstquestion

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstQuestionViewModel(
        application: Application,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        val userId: Int,
        val resultsDao: ResultsDao,
        val question: Int
) : AndroidViewModel(application) {
    private val _setImageEvent = MutableLiveData<Boolean>()
    private val _navigate = SingleLiveEvent<NavDirections>()

    val firstQuestionModel = FirstQuestionModel("")


    val setImageEvent: LiveData<Boolean>
        get() = _setImageEvent
    val navigate: LiveData<NavDirections>
        get() = _navigate


    init {
        _setImageEvent.value = true
        getQuestionText()
        setButtonText()
    }

    fun eventBack() {
         _navigate.postValue(FirstQuestionDirections.actionFirstQuestionToUserFragment(userId))
    }

    private fun getQuestionText() {
        viewModelScope.launch {
            val questions = getQuestion()
            firstQuestionModel.question = questions!!.text
        }
    }

    private fun setButtonText() {
        GlobalScope.launch(Dispatchers.Default) {
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
            if (list[i].question == question)
                rightAnswers.add(list[i])
        }

        return rightAnswers
    }

    fun navigateIfRightClick() {
        Log.d("%","${question%3}")
        if ((question % 3) == 0)
            _navigate.postValue(FirstQuestionDirections.actionFirstQuestionToPortalFragment(userId))
        else {
            writeAnswerToDataBase()
            _navigate.postValue(FirstQuestionDirections.actionFirstQuestionSelf(userId, question + 1))
        }
    }

    fun navigateIfWrongClick() {
        if ((question % 3) != 0)
            _navigate.postValue(FirstQuestionDirections.actionFirstQuestionSelf(userId, question + 1))
        else _navigate.postValue(FirstQuestionDirections.actionFirstQuestionToPortalFragment(userId))
    }

    //todo write right answer to database
    private fun writeAnswerToDataBase() {
        viewModelScope.launch {
            if (getResult(userId) == null) {
                val results = Results()
                results.user = userId
                results.score += 1F
                results.question += 1
                results.test += 1
                insertResult(results)
            } else {
                val results = getResult(userId)
                results!!.score += 1F
                results!!.test += 1
                results!!.question += 1
                updateResult(results!!)
            }
        }
    }

    private suspend fun getQuestion(): Questions? {
        return questionsDao.get(question.toLong())
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