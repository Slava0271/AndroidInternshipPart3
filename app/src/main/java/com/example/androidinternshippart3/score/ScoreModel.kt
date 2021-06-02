package com.example.androidinternshippart3.score

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScoreModel(
    private var _getScore: String = "",
    private var _getPercentage: String = "",
    private var _getTestPassed:String =""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var getScore: String = _getScore
        set(value) {
            _getScore = value
            field = value
            notifyPropertyChanged(BR.getScore)
        }

    @get:Bindable
    var getPercentage: String = _getPercentage
        set(value) {
            _getPercentage = value
            field = value
            notifyPropertyChanged(BR.getPercentage)
        }

    @get:Bindable
    var getTestPassed: String = _getTestPassed
        set(value) {
            _getTestPassed = value
            field = value
            notifyPropertyChanged(BR.getTestPassed)
        }


}