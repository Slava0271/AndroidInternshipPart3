package com.example.androidinternshippart3.secondtest.firstquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FourthQuestionModel(
    private var _fourthQuestion: String = ""
) : BaseObservable(), Parcelable {

    val answerGalaxy1: String = "J0313-1806"
    val answerGalaxy2: String = "Milky Way"
    val answerGalaxy3: String = "GN-z11"

    @get:Bindable
    var fourthQuestion: String = _fourthQuestion
        set(value) {
            _fourthQuestion = value
            field = value
            notifyPropertyChanged(BR.fourthQuestion)
        }
}