package com.example.androidinternshippart3.thirdtest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.androidinternshippart3.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EighthQuestionModel(
        private var _eighthQuestion: String = "",
        private var _eighthQuestionFirstAnswer: String = "",
        private var _eighthQuestionSecondAnswer: String = "",
        private var _eighthQuestionThirdAnswer: String = ""
) : BaseObservable(), Parcelable {


    @get:Bindable
    var eighthQuestion: String = _eighthQuestion
        set(value) {
            _eighthQuestion = value
            field = value
            notifyPropertyChanged(BR.eighthQuestion)
        }

    @get:Bindable
    var eighthQuestionFirstAnswer: String = _eighthQuestionFirstAnswer
        set(value) {
            _eighthQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.eighthQuestionFirstAnswer)
        }

    @get:Bindable
    var eighthQuestionSecondAnswer: String = _eighthQuestionSecondAnswer
        set(value) {
            _eighthQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.eighthQuestionSecondAnswer)
        }

    @get:Bindable
    var eighthQuestionThirdAnswer: String = _eighthQuestionThirdAnswer
        set(value) {
            _eighthQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.eighthQuestionThirdAnswer)
        }

}