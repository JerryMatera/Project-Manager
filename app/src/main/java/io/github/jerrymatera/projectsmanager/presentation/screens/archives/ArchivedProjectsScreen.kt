package io.github.jerrymatera.projectsmanager.presentation.screens.archives

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jerrymatera.projectsmanager.presentation.ui.components.ProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchivedProjectsScreen(
    state: ArchivedProjectUIState,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Archived Projects") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (state.archivedProjects.isEmpty()) {
                Text(text = "No archived projects")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(state.archivedProjects) { project ->
                        ProjectCard(project = project, onClick = { })
                    }
                }
            }

        }
    }
}