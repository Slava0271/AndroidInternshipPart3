package com.example.androidinternshippart3.checkErrors

data class CheckEmptyField(
        var _firstName: String,
        var _lastName: String,
        var _login: String,
        var _password: String,
        var _rePassword: String
) {
    fun checkEmptyField(): Boolean {
        return _firstName.isNotEmpty() && _lastName.isNotEmpty() && _login.isNotEmpty()
                && _password.isNotEmpty() && _rePassword.isNotEmpty()
    }

    fun checkEqualPassword(): Boolean {
        return _password == _rePassword && _password != ""
    }
}