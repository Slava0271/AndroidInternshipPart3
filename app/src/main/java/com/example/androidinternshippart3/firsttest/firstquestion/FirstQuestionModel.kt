package com.example.androidinternshippart3.firsttest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirstQuestionModel
    (private var _question: String = "") : BaseObservable(), Parcelable {

    val answerNairobi = "Nairobi"
    val answerSingapore = "Singapore"
    val answerParis = "Paris"

    @get:Bindable
    var question: String = _question
        set(value) {
            _question = value
            field = value
            notifyPropertyChanged(BR.question)
        }
}