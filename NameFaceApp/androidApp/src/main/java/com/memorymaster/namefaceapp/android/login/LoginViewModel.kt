package com.memorymaster.namefaceapp.android.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    var onNavigate: ((String) -> Unit)? = null

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                auth.signInWithEmailAndPassword(email.value, password.value).await()
                _loginState.value = LoginState.Success
                onNavigate?.invoke("home")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Onbekende fout")
            }
        }
    }

    fun onForgotPasswordClick() {
        viewModelScope.launch {
            try {
                auth.sendPasswordResetEmail(email.value).await()
                // Toon een bericht aan de gebruiker dat er een reset-e-mail is verzonden
            } catch (e: Exception) {
                // Toon een foutmelding aan de gebruiker
            }
        }
    }

    fun onCreateAccountClick() {
        onNavigate?.invoke("register")
    }

    fun onLanguageChange() {
        println("Taal wijzigen aangeroepen")
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

