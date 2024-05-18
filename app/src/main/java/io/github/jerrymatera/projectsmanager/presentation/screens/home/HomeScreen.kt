package io.github.jerrymatera.projectsmanager.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.jerrymatera.projectsmanager.presentation.navigation.ScreenRoutes
import io.github.jerrymatera.projectsmanager.presentation.ui.theme.ProjectsManagerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUIState,
    performEvent: (HomeEvent) -> Unit,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                username = state.user?.username ?: "",
                goToProfile = { /*TODO*/ },
                goToArchives = { navHostController.navigate(route = ScreenRoutes.Archives.route) })
        },
        floatingActionButton = {
            IconButton(onClick = { showBottomSheet = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Create New Project")
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
                IconButton(onClick = { showBottomSheet = true }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
            if (state.projects.isNullOrEmpty()) {
                EmptyItemCard(title = "You have no projects yet", onAddClick = { /*TODO*/ })
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.projects) { project ->
                        ProjectCard(project = project, onClick = { /*TODO*/ })
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
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
            if (state.tasks.isNullOrEmpty()) {
                EmptyItemCard(title = "You have no tasks yet", onAddClick = { /*TODO*/ })
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(state.tasks) { task ->
                        TaskCard(task = task)
                    }
                }
            }
        }


        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier  = Modifier.imePadding()
            )
            {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                        }
                    }
                    when {
                        state.projectCreated -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                            ) {
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
                        }

                        state.projectCreationError.isNotEmpty() -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    tint = MaterialTheme.colorScheme.error,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .padding(vertical = 16.dp)
                                )
                                Text(text = state.projectCreationError)
                                Button(onClick = { performEvent(HomeEvent.ClearProjectCreationError) },
                                    modifier = Modifier.padding(vertical = 16.dp)) {
                                    Text(text = "Try again")
                                }
                            }
                        }

                        else -> {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                OutlinedTextField(
                                    value = state.projectName,
                                    onValueChange = { performEvent(HomeEvent.UpdateProjectName(it)) },
                                    label = { Text("Project Name") },
                                    placeholder = { Text("Project Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                                OutlinedTextField(
                                    value = state.projectDescription,
                                    onValueChange = {
                                        performEvent(
                                            HomeEvent.UpdateProjectDescription(
                                                it
                                            )
                                        )
                                    },
                                    label = { Text("Project Description") },
                                    placeholder = { Text("Project Description") },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                                Button(
                                    enabled = state.projectName.isNotEmpty() && state.projectDescription.isNotEmpty(),
                                    onClick = { performEvent(HomeEvent.CreateProject) },
                                    modifier = Modifier.padding(vertical = 16.dp),
                                ) {
                                    Text(text = "Create Project")
                                }
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
            state = HomeUIState(),
            performEvent = {},
            navHostController = NavHostController(LocalContext.current)
        )
    }
}