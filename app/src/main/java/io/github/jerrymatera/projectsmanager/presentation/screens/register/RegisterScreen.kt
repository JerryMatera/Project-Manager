package io.github.jerrymatera.projectsmanager.presentation.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@Composable
fun RegisterScreen(
    state: RegisterUIState,
    performEvent: (RegisterUIEvent) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Text(text = "Welcome Onboard", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Create a new account",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = state.userName,
                    onValueChange = { performEvent(RegisterUIEvent.UpdateUserName(it)) },
                    label = { Text("Username") },
                    placeholder = { Text("Username") },
                )
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { performEvent(RegisterUIEvent.UpdateEmail(it)) },
                    label = { Text("Email") },
                    placeholder = { Text("Enter Email") },
                )
                OutlinedTextField(
                    value = state.phoneNumber,
                    onValueChange = { performEvent(RegisterUIEvent.UpdatePhone(it)) },
                    label = { Text("Phone Number") },
                    placeholder = { Text("Enter Phone Number") },
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { performEvent(RegisterUIEvent.UpdatePassword(it)) },
                    label = { Text("Password") },
                    placeholder = { Text("Enter Password") },
                )
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = { performEvent(RegisterUIEvent.UpdateConfirmPassword(it)) },
                    label = { Text("Confirm Password") },
                    placeholder = { Text("Confirm Password") },
                )
            }
            Button(onClick = { performEvent(RegisterUIEvent.Register) }) {
                Text(text = "Register")
            }
            Text(
                text = "Already have an account? Login",
                modifier = Modifier.clickable { onNavigateUp() })
        }
    }
    if (state.registerSuccess) {
        Dialog(onDismissRequest = { onNavigateUp() }) {
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(text = "Register Success")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onNavigateUp() }) {
                        Text(text = "Go to Login")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RegisterScreenPrev() {
    ProjectsManagerTheme {
        RegisterScreen(state = RegisterUIState(), performEvent = {}, onNavigateUp = { })
    }
}