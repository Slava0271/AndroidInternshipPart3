package com.example.androidinternshippart3.register

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterModel(
        private var _firstName: String = "",
        private var _lastName: String = "",
        private var _login: String = "",
        private var _password: String = "",
        private var _rePassword:String=""
) : BaseObservable(), Parcelable {
    @get:Bindable
    var firstName: String = _firstName
        set(value) {
            _firstName = value
            field = value
            notifyPropertyChanged(BR.firstName)
        }
    @get:Bindable
    var lastName: String = _lastName
        set(value) {
            _lastName = value
            field = value
            notifyPropertyChanged(BR.lastName)
        }
    @get:Bindable
    var login: String = _login
        set(value) {
            _login = value
            field = value
            notifyPropertyChanged(BR.login)
        }
    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var rePassword: String = _rePassword
        set(value) {
            _rePassword = value
            field = value
            notifyPropertyChanged(BR.rePassword)
        }


}
