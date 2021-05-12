package com.example.androidinternshippart3.secondtest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FifthQuestionModel(
    private var _fifthQuestion: String = ""
) : BaseObservable(), Parcelable {

    val firstAnswer: String = "33"
    val secondAnswer: String = "29"
    val thirdAnswer: String = "26"

    @get:Bindable
    var fifthQuestion: String = _fifthQuestion
        set(value) {
            _fifthQuestion = value
            field = value
            notifyPropertyChanged(BR.fifthQuestion)
        }
}