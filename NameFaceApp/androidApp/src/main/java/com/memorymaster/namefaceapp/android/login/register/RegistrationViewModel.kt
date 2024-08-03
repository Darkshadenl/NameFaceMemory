package com.memorymaster.namefaceapp.android.login.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegistrationViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    var onNavigate: ((String) -> Unit)? = null

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = performRegistration(_email.value, _password.value)

                if (result.isSuccess) {
                    onNavigate?.invoke("login")
                } else {
                    _errorMessage.value = "Registratie mislukt: ${result.error}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Er is een fout opgetreden: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun performRegistration(email: String, password: String): RegistrationResult {
        return if (email.isNotEmpty() && password.isNotEmpty()) {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
            } catch (e: Exception) {
                RegistrationResult(false, "Onbekende fout bij het aanmaken van account")
            }
            RegistrationResult(true, null)
        } else {
            RegistrationResult(false, "E-mail en wachtwoord mogen niet leeg zijn")
        }
    }
}

data class RegistrationResult(val isSuccess: Boolean, val error: String?)
