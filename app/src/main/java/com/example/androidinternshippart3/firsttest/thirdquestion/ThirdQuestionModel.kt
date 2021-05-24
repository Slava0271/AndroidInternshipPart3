package com.example.androidinternshippart3.firsttest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThirdQuestionModel(
        private var _thirdQuestionText: String = "",
        private var _firstAnswerQuestionThree: String = "",
        private var _secondAnswerQuestionThree: String = "",
        private var _thirdAnswerQuestionThree: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var thirdQuestionText: String = _thirdQuestionText
        set(value) {
            _thirdQuestionText = value
            field = value
            notifyPropertyChanged(BR.thirdQuestionText)
        }

    @get:Bindable
    var firstAnswerQuestionThree: String = _firstAnswerQuestionThree
        set(value) {
            _firstAnswerQuestionThree = value
            field = value
            notifyPropertyChanged(BR.firstAnswerQuestionThree)
        }

    @get:Bindable
    var secondAnswerQuestionThree: String = _secondAnswerQuestionThree
        set(value) {
            _secondAnswerQuestionThree = value
            field = value
            notifyPropertyChanged(BR.secondAnswerQuestionThree)
        }

    @get:Bindable
    var thirdAnswerQuestionThree: String = _thirdAnswerQuestionThree
        set(value) {
            _thirdAnswerQuestionThree = value
            field = value
            notifyPropertyChanged(BR.thirdAnswerQuestionThree)
        }
}