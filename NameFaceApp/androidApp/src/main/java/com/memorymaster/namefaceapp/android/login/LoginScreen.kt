package com.memorymaster.namefaceapp.android.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppLogo()
        AppName()
        EmailInput(email, viewModel::onEmailChange)
        PasswordInput(password, viewModel::onPasswordChange)
        LoginButton(viewModel::onLoginClick)
        ForgotPasswordLink(viewModel::onForgotPasswordClick)
        CreateAccountButton(viewModel::onCreateAccountClick)
        LanguageSelector(viewModel::onLanguageChange)
    }
}

@Composable
fun EmailInput(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("E-mail") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordInput(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Wachtwoord") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun LoginButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
    ) {
        Text("Inloggen", color = Color.White)
    }
}

@Composable
fun ForgotPasswordLink(onForgotPasswordClick: () -> Unit) {
    Text(
        text = "Wachtwoord vergeten?",
        color = Color(0xFF1A73E8),
        modifier = Modifier.clickable(onClick = onForgotPasswordClick)
    )
}

@Composable
fun CreateAccountButton(onCreateAccountClick: () -> Unit) {
    OutlinedButton(
        onClick = onCreateAccountClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Account aanmaken", color = Color(0xFF1A73E8))
    }
}

@Composable
fun LanguageSelector(onLanguageChange: () -> Unit) {
    Text(
        text = "Nederlands ▼",
        color = Color(0xFF5F6368),
        modifier = Modifier.clickable(onClick = onLanguageChange)
    )
}

@Composable
fun AppLogo() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color(0xFF4285F4), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "NFM",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AppName() {
    Text(
        text = "NameFaceMaster",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun EmailInput() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordInput() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("Password") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun LoginButton() {
    Button(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
    ) {
        Text("Log In", color = Color.White)
    }
}

@Composable
fun ForgotPasswordLink() {
    Text(
        text = "Forgot password?",
        color = Color(0xFF1A73E8),
        modifier = Modifier.clickable { }
    )
}

@Composable
fun CreateAccountButton() {
    OutlinedButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Create account", color = Color(0xFF1A73E8))
    }
}

@Composable
fun LanguageSelector() {
    Text(
        text = "Nederlands ▼",
        color = Color(0xFF5F6368),
        modifier = Modifier.clickable { }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}
