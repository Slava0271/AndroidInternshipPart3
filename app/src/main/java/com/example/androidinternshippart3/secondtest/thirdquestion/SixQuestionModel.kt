package com.example.androidinternshippart3.secondtest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SixQuestionModel(
    private var _sixQuestion: String = ""
) : BaseObservable(), Parcelable {


    val firstAnswer: String = "2"
    val secondAnswer: String = "5"
    val thirdAnswer: String = "6"

    @get:Bindable
    var sixQuestion: String = _sixQuestion
        set(value) {
            _sixQuestion = value
            field = value
            notifyPropertyChanged(BR.sixQuestion)
        }
}