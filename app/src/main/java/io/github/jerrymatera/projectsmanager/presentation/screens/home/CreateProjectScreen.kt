package io.github.jerrymatera.projectsmanager.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(
    state: HomeUIState,
    onDismiss: () -> Unit,
    performEvent: (HomeUIEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (state.projectName.isNotEmpty()) {
                        Text(text = state.projectName)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onDismiss() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.projectName,
                onValueChange = { performEvent(HomeUIEvent.UpdateProjectName(it)) },
                label = { Text("Project Name") },
                placeholder = { Text("Project Name") },
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = state.projectDescription,
                onValueChange = {
                    performEvent(
                        HomeUIEvent.UpdateProjectDescription(it)
                    )
                },
                label = { Text("Project Description") },
                placeholder = { Text("Project Description") },
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                enabled = state.projectName.isNotEmpty() && state.projectDescription.isNotEmpty(),
                onClick = { performEvent(HomeUIEvent.CreateProject) },
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Text(text = "Create Project")
            }

            if (state.projectCreated) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 16.dp)
                )
                Text(text = "Project created successfully")
            }
            if (state.projectCreationError.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(vertical = 16.dp)
                )
                Text(text = state.projectCreationError)
                Button(
                    onClick = { performEvent(HomeUIEvent.ClearProjectCreationError) },
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(text = "Try again")
                }

            }
        }
    }
}

@Preview
@Composable
private fun CreateProjectScreenPrev() {
    ProjectsManagerTheme {
        CreateProjectScreen(
            state = HomeUIState(),
            onDismiss = {},
            performEvent = {}
        )
    }
}