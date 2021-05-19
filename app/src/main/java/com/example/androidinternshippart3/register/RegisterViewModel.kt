package com.example.androidinternshippart3.register

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.androidinternshippart3.AddAnswers
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.checkErrors.CheckEmptyField
import com.example.androidinternshippart3.checkErrors.ErrorMessages
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.answers.Answers
import com.example.androidinternshippart3.database.answers.AnswersDao
import com.example.androidinternshippart3.database.question.Questions
import com.example.androidinternshippart3.database.question.QuestionsDao
import com.example.androidinternshippart3.database.tests.Tests
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import com.example.androidinternshippart3.lifecycle.SingleLiveEvent
import com.example.androidinternshippart3.readjson.ParseJson
import com.example.androidinternshippart3.roles.Roles
import kotlinx.coroutines.*


class RegisterViewModel(
        val usersDao: UsersDao,
        val accessDao: AccessDao,
        val testsDao: TestsDao,
        val questionsDao: QuestionsDao,
        val answersDao: AnswersDao,
        application: Application,
        private val fragmentManager: FragmentManager,
        val context: Context
) : AndroidViewModel(application), ShowDialog {

    val model = RegisterModel("", "", "", "", "")
    var dialog: Dialog? = null

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent


    init {
        viewModelScope.launch {
            addTestsAccessToDataBase()
            addTestsToDataBase()
            createAnswers()
        }
    }


    fun onButtonClick() {
        val checkEmptyField = CheckEmptyField(
                model.firstName,
                model.lastName,
                model.login,
                model.password,
                model.rePassword
        )
        val checkEmpty = checkEmptyField.checkEmptyField()
        val checkPasswords = checkEmptyField.checkEqualPassword()
        showDialogOrRegisterUser(checkEmpty, checkPasswords)
        Log.d("test", "{$model}")
    }

    private fun registerUser() {
        viewModelScope.launch {
            if (getByLogin(model.login) == null) {
                val user = Users()
                val access = Access()

                var role = Roles.USER.role
                if (get(1) == null)
                    role = Roles.ADMINISTRATOR.role
                user.setUser(model.firstName, model.lastName, model.login, model.password, role)
                updateUser(user)
                //todo
                access.setAccessTest1(user.usersId.toInt(), false, false, false)

                insertAccess(access)

                insert(user)
                showDialog(ErrorMessages.SUCCESS.message)
                _navigationEvent.postValue(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            } else showDialog(ErrorMessages.ALREADY_EXIST.message)
        }
    }

    private fun sleep(): Int {
        Thread.sleep(2500)
        return 0
    }

    private suspend fun addTestsToDataBase() {
        if (getQuestion(1) == null) {
            readTestsAndAddToDataBase("questions_first_test.json", 1)
            readTestsAndAddToDataBase("questions_second_test.json", 2)
            readTestsAndAddToDataBase("question_third_test.json", 3)

        }
    }

    private suspend fun createAnswers() {
        if (getAnswer(1) == null) {
            val addAnswers = AddAnswers(answersDao)
            addAnswers.addAnswers()
            Log.d("test", getAnswer(1)?.text.toString())
        }
    }

    private suspend fun readTestsAndAddToDataBase(string: String, test: Int) {
        val questionsList = ParseJson.getObjectsJSON(context, string)
        for (i in 0 until questionsList.size) {
            val question = Questions()
            question.setQuestion(questionsList[i].question, 1, test)
            updateQuestion(question)
            insertQuestion(question)
        }
    }


    private suspend fun addTestsAccessToDataBase() {
        if (getTest(1) == null) {
            val firstTest = Tests()
            firstTest.description = " first test"
            insertTest(firstTest)
            val secondTest = Tests()
            secondTest.description = "second test"
            insertTest(secondTest)
            val thirdTest = Tests()
            thirdTest.description = "third test"
            insertTest(thirdTest)
        }

    }

    private suspend fun getByLogin(login: String): Users? {
        return usersDao.getByLogin(login)
    }

    private suspend fun getTest(long: Long): Tests? {
        return testsDao.get(long)
    }

    private suspend fun updateUser(users: Users) {
        usersDao.update(users)
    }

    private suspend fun insert(users: Users) {
        usersDao.insert(users)
    }

    private suspend fun insertQuestion(questions: Questions) {
        questionsDao.insert(questions)
    }

    private suspend fun updateQuestion(questions: Questions) {
        questionsDao.update(questions)
    }

    private suspend fun insertAccess(access: Access) {
        accessDao.insert(access)
    }

    private suspend fun insertAnswer(answers: Answers) {
        answersDao.insert(answers)
    }

    private suspend fun insertTest(tests: Tests) {
        testsDao.insert(tests)
    }

    private suspend fun getQuestion(long: Long): Questions? {
        return questionsDao.get(long)
    }

    private suspend fun clear() {
        usersDao.clear()
    }

    private suspend fun get(long: Long): Users? {
        return usersDao.get(long)
    }

    private suspend fun getAnswer(long: Long): Answers? {
        return answersDao.get(long)
    }

    private fun showDialogOrRegisterUser(checkEmpty: Boolean, checkPasswords: Boolean) {
        if (!checkPasswords && !checkEmpty)
            showDialog(ErrorMessages.PASSWORDS_AND_FIELD.message)
        else if (!checkPasswords)
            showDialog(ErrorMessages.PASSWORDS.message)
        else if (!checkEmpty)
            showDialog(ErrorMessages.FIELDS.message)
        else registerUser()

        viewModelScope.launch {
            sleepAndClearFields()
        }

    }

    private suspend fun sleepAndClearFields() {
        val job = Dispatchers.Main {
            val deferred1 = async(Dispatchers.Default) { sleep() }
            deferred1.await()
            clearFields()
        }
    }

    private fun clearFields() {
        model.firstName = ""
        model.lastName = ""
        model.login = ""
        model.password = ""
        model.rePassword = ""
    }

    override fun showDialog(string: String) {
        dialog = Dialog(string)
        dialog!!.show(fragmentManager, "")
    }


}