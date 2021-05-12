package com.example.androidinternshippart3.thirdtest.secondquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.androidinternshippart3.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EighthQuestionModel(
    private var _eighthQuestion: String = ""
) : BaseObservable(), Parcelable {

    val firstAnswer: String = "Chomolungma"
    val secondAnswer: String = "Kanchenjunga"
    val thirdAnswer: String = "Makalu"

    @get:Bindable
    var eighthQuestion: String = _eighthQuestion
        set(value) {
            _eighthQuestion = value
            field = value
            notifyPropertyChanged(BR.eighthQuestion)
        }
}