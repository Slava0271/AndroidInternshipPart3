package com.example.androidinternshippart3.secondtest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FourthQuestionModel(
        private var _fourthQuestion: String = "",
        private var _fourthQuestionFirstAnswer: String = "",
        private var _fourthQuestionSecondAnswer: String = "",
        private var _fourthQuestionThirdAnswer: String = ""
) : BaseObservable(), Parcelable {


    @get:Bindable
    var fourthQuestion: String = _fourthQuestion
        set(value) {
            _fourthQuestion = value
            field = value
            notifyPropertyChanged(BR.fourthQuestion)
        }

    @get:Bindable
    var fourthQuestionFirstAnswer: String = _fourthQuestionFirstAnswer
        set(value) {
            _fourthQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.fourthQuestionFirstAnswer)
        }

    @get:Bindable
    var fourthQuestionSecondAnswer: String = _fourthQuestionSecondAnswer
        set(value) {
            _fourthQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.fourthQuestionSecondAnswer)
        }

    @get:Bindable
    var fourthQuestionThirdAnswer: String = _fourthQuestionThirdAnswer
        set(value) {
            _fourthQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.fourthQuestionThirdAnswer)
        }
}