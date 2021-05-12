package com.example.androidinternshippart3.thirdtest.thirdquestion

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NineQuestionModel(
    private var _nineQuestion: String = ""
) : BaseObservable(), Parcelable {


    val firstAnswer: String = "Neil Armstrong"
    val secondAnswer: String = "Kurt Cobain"
    val thirdAnswer: String = "Yuri Gagarin"

    @get:Bindable
    var nineQuestion: String = _nineQuestion
        set(value) {
            _nineQuestion = value
            field = value
            notifyPropertyChanged(BR.nineQuestion)
        }
}