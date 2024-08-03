package com.memorymaster.namefaceapp.android.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick() {
        println("Login aangeroepen met email: ${email.value} en wachtwoord: ${password.value}")
    }

    fun onForgotPasswordClick() {
        println("Wachtwoord vergeten aangeroepen")
    }

    fun onCreateAccountClick() {
        println("Account aanmaken aangeroepen")
    }

    fun onLanguageChange() {
        println("Taal wijzigen aangeroepen")
    }
}
