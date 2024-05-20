package io.github.jerrymatera.projectsmanager.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.jerrymatera.projectsmanager.data.network.model.ArchivedStatus
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.network.model.Task
import io.github.jerrymatera.projectsmanager.presentation.ui.components.EmptyItemCard
import io.github.jerrymatera.projectsmanager.presentation.ui.components.ScreenSection
import io.github.jerrymatera.projectsmanager.presentation.ui.components.TaskCard
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreen(
    state: ProjectUIState,
    performEvent: (ProjectUIEvent) -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    when {
        state.createTask -> {
            CreateTaskScreen(state = state, performEvent = performEvent)
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = state.project!!.name) },
                        navigationIcon = {
                            IconButton(onClick = { navHostController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                },
                floatingActionButton = {
                    IconButton(onClick = { performEvent(ProjectUIEvent.ShowCreateTaskScreen) }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    ScreenSection(
                        sectionTitle = "Project Description",
                        content = {
                            Text(
                                text = state.project!!.description,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            )
                        }
                    )
                    ScreenSection(
                        sectionTitle = "Created At:",
                        content = {
                            Text(
                                text = state.project!!.createdAt,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp)
                            )
                        }
                    )
                    ScreenSection(
                        sectionTitle = "Tasks",
                        content = {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {
                                if (state.projectTasks.isEmpty()) {
                                    item {
                                        EmptyItemCard(
                                            title = "No tasks Yet",
                                            onAddClick = { performEvent(ProjectUIEvent.ShowCreateTaskScreen) })
                                    }
                                } else {
                                    items(state.projectTasks) { task ->
                                        TaskCard(task = task, hideProjectNames = true)
                                    }
                                }

                            }
                        }
                    )
                    if (state.archivedTasks.isNotEmpty()) {
                        ScreenSection(
                            sectionTitle = "Archived Tasks",
                            content = {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    contentPadding = PaddingValues(vertical = 16.dp)
                                ) {
                                    items(state.archivedTasks) { task ->
                                        TaskCard(task = task, hideProjectNames = true)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProjectScreenPrev() {
    ProjectsManagerTheme {
        ProjectScreen(
            state = ProjectUIState(
                project = Project(
                    uuid = "68177f80-f3d9-4e44-ab17-abc486f9eace",
                    name = "ProjectY",
                    description = "A project to scan the Y future",
                    userUuid = "30e14a03-597e-4dda-ad59-a34caa629895",
                    createdAt = "2024-05-15T14:57:24Z",
                    archivedStatus = ArchivedStatus(
                        time = "0001-01-01T00:00:00Z",
                        valid = false
                    )
                ),
                projectTasks = listOf(
                    Task(
                        uuid = "193dd254-95c5-4e24-ac3f-95975f1a03bc",
                        name = "Design",
                        deadline = "2024-06-01T02:00:00Z",
                        createdAt = "2024-05-15T15:50:35Z",
                        projectUuid = "2bc9fbde-8634-4806-9b20-622be40bc718",
                        archivedStatus = ArchivedStatus(
                            time = "0001-01-01T00:00:00Z",
                            valid = false
                        ),
                        projectName = "ProjectAI"
                    ),
                    Task(
                        uuid = "193dd254-95c5-4e24-ac3f-95975f1a03bc",
                        name = "Design",
                        deadline = "2024-06-01T02:00:00Z",
                        createdAt = "2024-05-15T15:50:35Z",
                        projectUuid = "2bc9fbde-8634-4806-9b20-622be40bc718",
                        archivedStatus = ArchivedStatus(
                            time = "0001-01-01T00:00:00Z",
                            valid = false
                        ),
                        projectName = "ProjectAI"
                    ),
                    Task(
                        uuid = "193dd254-95c5-4e24-ac3f-95975f1a03bc",
                        name = "Design",
                        deadline = "2024-06-01T02:00:00Z",
                        createdAt = "2024-05-15T15:50:35Z",
                        projectUuid = "2bc9fbde-8634-4806-9b20-622be40bc718",
                        archivedStatus = ArchivedStatus(
                            time = "0001-01-01T00:00:00Z",
                            valid = false
                        ),
                        projectName = "ProjectAI"
                    ),
                    Task(
                        uuid = "193dd254-95c5-4e24-ac3f-95975f1a03bc",
                        name = "Design",
                        deadline = "2024-06-01T02:00:00Z",
                        createdAt = "2024-05-15T15:50:35Z",
                        projectUuid = "2bc9fbde-8634-4806-9b20-622be40bc718",
                        archivedStatus = ArchivedStatus(
                            time = "0001-01-01T00:00:00Z",
                            valid = false
                        ),
                        projectName = "ProjectAI"
                    )
                )
            ),
            performEvent = {},
            navHostController = NavHostController(LocalContext.current)
        )
    }
}