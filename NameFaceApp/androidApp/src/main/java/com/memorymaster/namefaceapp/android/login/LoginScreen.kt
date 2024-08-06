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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.memorymaster.namefaceapp.android.R

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), nav:NavHostController) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    viewModel.onNavigate = { route ->
        nav.navigate(route)
    }

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
        when (loginState) {
            is LoginState.Loading -> CircularProgressIndicator()
            is LoginState.Error -> Text("Fout: ${(loginState as LoginState.Error).message}")
            is LoginState.Success -> Text("Succesvol ingelogd!")
            else -> {}
        }
//        ForgotPasswordLink(viewModel::onForgotPasswordClick)
        CreateAccountButton(viewModel::onCreateAccountClick)
//        LanguageSelector(viewModel::onLanguageChange)
    }
}

@Composable
fun EmailInput(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(stringResource(R.string.email)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordInput(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.password)) },
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
        Text(stringResource(R.string.login), color = Color.White)
    }
}

@Composable
fun ForgotPasswordLink(onForgotPasswordClick: () -> Unit) {
    Text(
        text = stringResource(R.string.forgot_password),
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
        Text(stringResource(R.string.create_account), color = Color(0xFF1A73E8))
    }
}

@Composable
fun LanguageSelector(onLanguageChange: () -> Unit) {
    Text(
        text = stringResource(R.string.language_selector),
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

    }
}

@Composable
fun AppName() {
    Text(
        text = stringResource(R.string.app_name),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        LoginScreen(nav = navController)
    }
}

