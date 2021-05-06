package com.example.androidinternshippart3.checkErrors

enum class ErrorMessages(val message: String) {
    PASSWORDS_AND_FIELD("You have not completed the input fields \nYour passwords do not match"),
    PASSWORDS("Your passwords do not match"),
    FIELDS("You have not completed the input fields"),
    USER_NOT_EXIST("This user is not registered or your password wrong"),
    SUCCESS("Your account is registered"),
    ALREADY_EXIST("a user with this login already exists")
}