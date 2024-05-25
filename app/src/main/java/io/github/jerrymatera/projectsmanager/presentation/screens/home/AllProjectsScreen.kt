package io.github.jerrymatera.projectsmanager.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.jerrymatera.projectsmanager.data.network.model.ArchivedStatus
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.domain.toRoute
import io.github.jerrymatera.projectsmanager.presentation.ui.components.ProjectCard
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProjectsScreen(
    projects: List<Project>,
    onNavigateUp: () -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Projects") },
                navigationIcon = {
                    IconButton(onClick = {onNavigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(projects) { project ->
                ProjectCard(
                    project = project,
                    onClick = { navHostController.navigate(project.toRoute()) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun AllProjectsScreenPrev() {
    ProjectsManagerTheme {
        AllProjectsScreen(
            projects = listOf(
                Project(
                    uuid = "68177f80-f3d9-4e44-ab17-abc486f9eace",
                    name = "ProjectY",
                    description = "A project to scan the Y future with a slightly long description but you get what i'm saying",
                    userUuid = "30e14a03-597e-4dda-ad59-a34caa629895",
                    createdAt = "2024-05-15T14:57:24Z",
                    archivedStatus = ArchivedStatus(
                        time = "0001-01-01T00:00:00Z",
                        valid = false
                    )
                ),
                Project(
                    uuid = "68177f80-f3d9-4e44-ab17-abc486f9eace",
                    name = "ProjectY",
                    description = "A project to scan the Y future with a slightly long description",
                    userUuid = "30e14a03-597e-4dda-ad59-a34caa629895",
                    createdAt = "2024-05-15T14:57:24Z",
                    archivedStatus = ArchivedStatus(
                        time = "0001-01-01T00:00:00Z",
                        valid = false
                    )
                )
            ),
            onNavigateUp = {},
            navHostController = NavHostController(LocalContext.current)
        )
    }
}