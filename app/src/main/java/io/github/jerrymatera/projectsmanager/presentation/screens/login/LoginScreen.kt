package io.github.jerrymatera.projectsmanager.presentation.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.jerrymatera.projectsmanager.presentation.navigation.Home
import io.github.jerrymatera.projectsmanager.presentation.navigation.Register

@Composable
fun LoginScreen(
    state: LoginUIState,
    performEvent: (LoginEvent) -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    if (state.authenticated) {
        navHostController.navigate(Home) {
            popUpToRoute
        }
    }
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
                    value = state.email,
                    onValueChange = { performEvent(LoginEvent.UpdateUsernameOrEmail(it)) },
                    label = { Text(" Email") },
                    placeholder = { Text("Enter Email") },
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { performEvent(LoginEvent.UpdatePassword(it)) },
                    label = { Text("Password") },
                    placeholder = { Text("Enter Password") },
                )
            }

            Button(onClick = { performEvent(LoginEvent.Login) }) {
                Text(text = "Login")
            }
            Text(
                text = "Don't have an account? Sign up",
                modifier = Modifier.clickable { navHostController.navigate(Register) })
        }
    }
}