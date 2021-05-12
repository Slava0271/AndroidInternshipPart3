package com.example.androidinternshippart3.firsttest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThirdQuestionModel(
    private var _thirdQuestionText: String = ""
) : BaseObservable(), Parcelable {
    val answerUSA : String = "USA"
    val answerSpain :String = "Spain"
    val answerUruguay:String = "Uruguay"

    @get:Bindable
    var thirdQuestionText: String = _thirdQuestionText
        set(value) {
            _thirdQuestionText = value
            field = value
            notifyPropertyChanged(BR.thirdQuestionText)
        }
}