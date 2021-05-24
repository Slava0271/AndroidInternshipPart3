package com.example.androidinternshippart3.thirdtest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NineQuestionModel(
        private var _nineQuestion: String = "",
        private var _nineQuestionFirstAnswer: String = "",
        private var _nineQuestionSecondAnswer: String = "",
        private var _nineQuestionThirdAnswer: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var nineQuestion: String = _nineQuestion
        set(value) {
            _nineQuestion = value
            field = value
            notifyPropertyChanged(BR.nineQuestion)
        }

    @get:Bindable
    var nineQuestionFirstAnswer: String = _nineQuestionFirstAnswer
        set(value) {
            _nineQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.nineQuestionFirstAnswer)
        }

    @get:Bindable
    var nineQuestionSecondAnswer: String = _nineQuestionSecondAnswer
        set(value) {
            _nineQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.nineQuestionSecondAnswer)
        }

    @get:Bindable
    var nineQuestionThirdAnswer: String = _nineQuestionThirdAnswer
        set(value) {
            _nineQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.nineQuestionThirdAnswer)
        }

}