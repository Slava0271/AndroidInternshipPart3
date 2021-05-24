package com.example.androidinternshippart3.firsttest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SecondQuestionModel(
        private var _secondQuestionText: String = "",
        private var _fourAnswer: String = "",
        private var _fiveAnswer: String = "",
        private var _sixAnswer: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var secondQuestionText: String = _secondQuestionText
        set(value) {
            _secondQuestionText = value
            field = value
            notifyPropertyChanged(BR.secondQuestionText)
        }

    @get:Bindable
    var fourAnswer: String = _fourAnswer
        set(value) {
            _fourAnswer = value
            field = value
            notifyPropertyChanged(BR.fourAnswer)
        }

    @get:Bindable
    var fiveAnswer: String = _fiveAnswer
        set(value) {
            _fiveAnswer = value
            field = value
            notifyPropertyChanged(BR.fiveAnswer)
        }

    @get:Bindable
    var sixAnswer: String = _sixAnswer
        set(value) {
            _sixAnswer = value
            field = value
            notifyPropertyChanged(BR.sixAnswer)
        }

}