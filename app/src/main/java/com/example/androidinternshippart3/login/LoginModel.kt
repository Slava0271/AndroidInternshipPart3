package com.example.androidinternshippart3.login

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
        private var _login: String = "",
        private var _password: String = ""
) : BaseObservable(), Parcelable {
    @get:Bindable
    var login2: String = _login
        set(value) {
            _login = value
            field = value
            notifyPropertyChanged(BR.login2)
        }

    @get:Bindable
    var password2: String = _password
        set(value) {
            _password = value
            field = value
            notifyPropertyChanged(BR.password2)
        }
}