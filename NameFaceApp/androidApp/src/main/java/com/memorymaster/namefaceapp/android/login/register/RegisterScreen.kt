package com.memorymaster.namefaceapp.android.login.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.memorymaster.namefaceapp.android.R

@Composable
fun RegisterScreen(viewModel: RegistrationViewModel = viewModel(), nav: NavHostController) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    viewModel.onNavigate = { route ->
        nav.navigate(route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light),
            modifier = Modifier.padding(bottom = 30.dp)
        )

        FloatingLabelTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = stringResource(R.string.email_address),
        )

        Spacer(modifier = Modifier.height(20.dp))

        FloatingLabelTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = stringResource(R.string.password),
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { viewModel.onRegisterClick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
        ) {
            Text(stringResource(R.string.register), color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { nav.navigate("login") }) {
            Text(
                text = stringResource(R.string.already_account),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.clickable { nav.navigate("login") }
            )
        }
    }
}

@Composable
fun FloatingLabelTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }

    Box() {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
                .background(Color.Transparent)
                .padding(vertical = 8.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )

        Text(
            text = label,
            color = if (isFocused || value.isNotEmpty()) Color(0xFF2196F3) else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(if (isFocused || value.isNotEmpty()) Alignment.TopStart else Alignment.CenterStart)
                .offset(y = if (isFocused || value.isNotEmpty()) (-24).dp else 0.dp)
        )

        HorizontalDivider(
            color = if (isFocused) Color(0xFF2196F3) else Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        RegisterScreen(nav = navController)
    }
}
