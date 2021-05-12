package com.example.androidinternshippart3.thirdtest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SevenQuestionModel(
    private var _sevenQuestion: String = ""
) : BaseObservable(), Parcelable {


    val firstAnswer: String = "Tokyo"
    val secondAnswer: String = "New-York"
    val thirdAnswer: String = "Kiev"

    @get:Bindable
    var sevenQuestion: String = _sevenQuestion
        set(value) {
            _sevenQuestion = value
            field = value
            notifyPropertyChanged(BR.sevenQuestion)
        }
}