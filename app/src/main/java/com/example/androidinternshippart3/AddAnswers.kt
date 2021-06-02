package com.example.androidinternshippart3

import com.example.androidinternshippart3.database.answers.Answers
import com.example.androidinternshippart3.database.answers.AnswersDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class AddAnswers(val answersDao: AnswersDao) {
    suspend fun addAnswers() {
        insert(createAnswer("Nairobi", 0f, 1, 0))
        insert(createAnswer("Singapore", 0f, 1, 0))
        insert(createAnswer("Paris", 0f, 1, 0))
        insert(createAnswer("4", 0f, 2, 0))
        insert(createAnswer("5", 0f, 2, 0))
        insert(createAnswer("6", 0f, 2, 0))
        insert(createAnswer("USA", 0f, 3, 0))
        insert(createAnswer("Spain", 0f, 3, 0))
        insert(createAnswer("Uruguay", 0f, 3, 0))
        insert(createAnswer("J0313-1806", 0f, 4, 0))
        insert(createAnswer("Milky Way", 0f, 4, 0))
        insert(createAnswer("GN-z11", 0f, 4, 0))
        insert(createAnswer("33", 0f, 5, 0))
        insert(createAnswer("29", 0f, 5, 0))
        insert(createAnswer("26", 0f, 5, 0))
        insert(createAnswer("2", 0f, 6, 0))
        insert(createAnswer("5", 0f, 6, 0))
        insert(createAnswer("6", 0f, 6, 0))
        insert(createAnswer("Tokyo", 0f, 7, 0))
        insert(createAnswer("New-York", 0f, 7, 0))
        insert(createAnswer("Kiev", 0f, 7, 0))
        insert(createAnswer("Chomolungma", 0f, 8, 0))
        insert(createAnswer("Kanchenjunga", 0f, 8, 0))
        insert(createAnswer("Makalu", 0f, 8, 0))
        insert(createAnswer("Neil Armstrong", 0f, 9, 0))
        insert(createAnswer("Kurt Cobain", 0f, 9, 0))
        insert(createAnswer("Yuri Gagarin", 0f, 9, 0))

    }

    private suspend fun createAnswer(
        text: String,
        score: Float,
        question: Int,
        name: Int
    ): Answers {
        val answers = Answers()
        answers.setAnswer(text, score, question, name)
        update(answers)
        return answers
    }

    private suspend fun insert(answers: Answers) {
        answersDao.insert(answers)
    }

    private suspend fun update(answers: Answers) {
        answersDao.update(answers)
    }
}