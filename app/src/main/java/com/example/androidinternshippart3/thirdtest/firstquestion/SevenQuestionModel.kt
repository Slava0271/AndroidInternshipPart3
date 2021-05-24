package com.example.androidinternshippart3.thirdtest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SevenQuestionModel(
    private var _sevenQuestion: String = "",
    private var _sevenQuestionFirstAnswer:String = "",
    private var _sevenQuestionSecondAnswer:String = "",
    private var _sevenQuestionThirdAnswer:String = ""
    ) : BaseObservable(), Parcelable {


    @get:Bindable
    var sevenQuestion: String = _sevenQuestion
        set(value) {
            _sevenQuestion = value
            field = value
            notifyPropertyChanged(BR.sevenQuestion)
        }
    @get:Bindable
    var sevenQuestionFirstAnswer: String = _sevenQuestionFirstAnswer
        set(value) {
            _sevenQuestionFirstAnswer = value
            field = value
            notifyPropertyChanged(BR.sevenQuestionFirstAnswer)
        }

    @get:Bindable
    var sevenQuestionSecondAnswer: String = _sevenQuestionSecondAnswer
        set(value) {
            _sevenQuestionSecondAnswer = value
            field = value
            notifyPropertyChanged(BR.sevenQuestionSecondAnswer)
        }

    @get:Bindable
    var sevenQuestionThirdAnswer: String = _sevenQuestionThirdAnswer
        set(value) {
            _sevenQuestionThirdAnswer = value
            field = value
            notifyPropertyChanged(BR.sevenQuestionThirdAnswer)
        }

}