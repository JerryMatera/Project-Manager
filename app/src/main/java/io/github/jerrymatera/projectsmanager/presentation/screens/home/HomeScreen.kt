package io.github.jerrymatera.projectsmanager.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.jerrymatera.projectsmanager.data.network.model.ArchivedStatus
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.network.model.Task
import io.github.jerrymatera.projectsmanager.domain.toRoute
import io.github.jerrymatera.projectsmanager.presentation.navigation.Archives
import io.github.jerrymatera.projectsmanager.presentation.ui.components.EmptyItemCard
import io.github.jerrymatera.projectsmanager.presentation.ui.components.ProjectCard
import io.github.jerrymatera.projectsmanager.presentation.ui.components.TaskCard
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme

@Composable
fun HomeScreen(
    state: HomeUIState,
    performEvent: (HomeUIEvent) -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var viewAllProjects by rememberSaveable { mutableStateOf(false) }

    if (showBottomSheet) {
        CreateProjectScreen(
            state = state,
            onDismiss = { showBottomSheet = false },
            performEvent = performEvent
        )
    } else {
        if (viewAllProjects) {
            state.projects?.let {
                AllProjectsScreen(
                    projects = it,
                    navHostController = navHostController,
                    onNavigateUp = { viewAllProjects = false })
            }
        } else {
            Scaffold(
                topBar = {
                    HomeTopAppBar(
                        username = state.user?.username ?: "",
                        goToProfile = { },
                        goToArchives = { navHostController.navigate(Archives) })
                },
                floatingActionButton = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Create New Project"
                        )
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Projects")
                        if (!state.projects.isNullOrEmpty()) {
                            IconButton(onClick = { viewAllProjects = true }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    if (state.projects.isNullOrEmpty()) {
                        EmptyItemCard(
                            title = "You have no projects yet",
                            onAddClick = { showBottomSheet = true })
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(state.projects) { project ->
                                ProjectCard(
                                    project = project,
                                    onClick = { navHostController.navigate(project.toRoute()) }
                                )
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Recent tasks")
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }
                    if (state.tasks.isNullOrEmpty()) {
                        EmptyItemCard(title = "You have no tasks yet", onAddClick = { /*TODO*/ })
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(state.tasks) { task ->
                                TaskCard(task = task)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    username: String,
    goToProfile: () -> Unit,
    goToArchives: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "Hi, $username") },
        navigationIcon = {
            IconButton(onClick = { goToProfile() }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("View Archives") },
                    onClick = { goToArchives() },
                )
            }
        }
    )
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    ProjectsManagerTheme {
        HomeScreen(
            state = HomeUIState(
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
                tasks = listOf(
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