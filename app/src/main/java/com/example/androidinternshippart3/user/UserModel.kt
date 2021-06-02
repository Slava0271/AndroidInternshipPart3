package com.example.androidinternshippart3.user

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    private var _helloUser: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var helloUser: String = _helloUser
        set(value) {
            _helloUser = value
            field = value
            notifyPropertyChanged(BR.helloUser)
        }
}