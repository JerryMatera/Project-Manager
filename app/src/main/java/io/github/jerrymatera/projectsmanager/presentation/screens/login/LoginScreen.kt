package io.github.jerrymatera.projectsmanager.presentation.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = state.usernameOrEmail,
                onValueChange = { performEvent(LoginEvent.UpdateUsernameOrEmail(it)) },
                label = { Text("Username / Email") },
                placeholder = { Text("Username / Email") },
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { performEvent(LoginEvent.UpdatePassword(it)) },
                label = { Text("Password") },
                placeholder = { Text("Password") },
            )
            Button(onClick = { performEvent(LoginEvent.Login) }) {
                Text(text = "Login")
            }
            Text(
                text = "Don't have an account? Sign up",
                modifier = Modifier.clickable { navHostController.navigate(Register) })
        }
    }
}