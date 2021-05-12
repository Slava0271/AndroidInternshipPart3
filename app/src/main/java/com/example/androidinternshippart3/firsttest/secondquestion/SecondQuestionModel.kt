package com.example.androidinternshippart3.firsttest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SecondQuestionModel(
    private var _secondQuestionText: String = ""
) : BaseObservable(), Parcelable {
    val fourAnswer = "4"
    val fiveAnswer = "5"
    val sixAnswer = "6"

    @get:Bindable
    var secondQuestionText: String = _secondQuestionText
        set(value) {
            _secondQuestionText = value
            field = value
            notifyPropertyChanged(BR.secondQuestionText)
        }
}