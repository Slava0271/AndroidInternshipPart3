package com.example.androidinternshippart3.secondtest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FifthQuestionModel(
        private var _fifthQuestion: String = "",
        private var _fifthQuestionFirstAnswer: String = "",
        private var _fifthQuestionSecondAnswer: String = "",
        private var _fifthQuestionThirdAnswer: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var fifthQuestion: String = _fifthQuestion
        set(value) {
            _fifthQuestion = value
            field = value
            notifyPropertyChanged(BR.fifthQuestion)
        }

    @get:Bindable
    var fifthQuestionFirstAnswer: String = _fifthQuestionFirstAnswer
        set(value) {
            _fifthQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.fifthQuestionFirstAnswer)
        }

    @get:Bindable
    var fifthQuestionSecondAnswer: String = _fifthQuestionSecondAnswer
        set(value) {
            _fifthQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.fifthQuestionSecondAnswer)
        }

    @get:Bindable
    var fifthQuestionThirdAnswer: String = _fifthQuestionThirdAnswer
        set(value) {
            _fifthQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.fifthQuestionThirdAnswer)
        }
}