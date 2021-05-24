package com.example.androidinternshippart3.firsttest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirstQuestionModel
    (
    private var _question: String = "",
    private var _firstAnswer: String = "",
    private var _secondAnswer: String = "",
    private var _thirdAnswer: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var question: String = _question
        set(value) {
            _question = value
            field = value
            notifyPropertyChanged(BR.question)
        }

    @get:Bindable
    var firstAnswer: String = _firstAnswer
        set(value) {
            _firstAnswer = value
            field = value
            notifyPropertyChanged(BR.firstAnswer)
        }

    @get:Bindable
    var secondAnswer: String = _secondAnswer
        set(value) {
            _secondAnswer = value
            field = value
            notifyPropertyChanged(BR.secondAnswer)
        }

    @get:Bindable
    var thirdAnswer: String = _thirdAnswer
        set(value) {
            _thirdAnswer = value
            field = value
            notifyPropertyChanged(BR.thirdAnswer)
        }

}