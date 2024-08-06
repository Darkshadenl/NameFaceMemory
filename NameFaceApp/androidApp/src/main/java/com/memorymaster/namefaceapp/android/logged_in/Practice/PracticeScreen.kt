package com.memorymaster.namefaceapp.android.logged_in.Practice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PracticeScreen(nav: NavHostController) {
    var currentName by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") }
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Topbalk met terug-knop en voortgangsbalk
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { nav.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Terug")
            }
            LinearProgressIndicator(
                progress = {
                    0.5f // Dit zou dynamisch moeten zijn
                },
                modifier = Modifier.width(200.dp),
            )
        }

        // Gezicht (of initiaal) weergave
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.LightGray, shape = CircleShape)
                .border(2.dp, Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentName.firstOrNull()?.uppercase() ?: "",
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Invoerveld voor de naam
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Voer de naam in") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Controleer knop
        Button(
            onClick = {
                isCorrect = userInput.equals(currentName, ignoreCase = true)
                showFeedback = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Controleer")
        }

        // Feedback
        if (showFeedback) {
            Text(
                text = if (isCorrect) "Correct!" else "Onjuist, probeer opnieuw.",
                color = if (isCorrect) Color.Green else Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PracticeScreenPreview() {
    val dummyNavController = rememberNavController()
    MaterialTheme {
        PracticeScreen(nav = dummyNavController)
    }
}
