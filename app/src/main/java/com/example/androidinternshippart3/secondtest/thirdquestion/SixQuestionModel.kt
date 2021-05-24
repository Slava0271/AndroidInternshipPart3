package com.example.androidinternshippart3.secondtest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SixQuestionModel(
        private var _sixQuestion: String = "",
        private var _sixQuestionFirstAnswer: String = "",
        private var _sixQuestionSecondAnswer: String = "",
        private var _sixQuestionThirdAnswer: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var sixQuestion: String = _sixQuestion
        set(value) {
            _sixQuestion = value
            field = value
            notifyPropertyChanged(BR.sixQuestion)
        }

    @get:Bindable
    var sixQuestionFirstAnswer: String = _sixQuestionFirstAnswer
        set(value) {
            _sixQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.sixQuestionFirstAnswer)
        }

    @get:Bindable
    var sixQuestionSecondAnswer: String = _sixQuestionSecondAnswer
        set(value) {
            _sixQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.sixQuestionSecondAnswer)
        }

    @get:Bindable
    var sixQuestionThirdAnswer: String = _sixQuestionThirdAnswer
        set(value) {
            _sixQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.sixQuestionThirdAnswer)
        }
}