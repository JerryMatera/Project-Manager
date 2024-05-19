package io.github.jerrymatera.projectsmanager.presentation.screens.project

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jerrymatera.projectsmanager.presentation.ui.components.ScreenSection
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    state: ProjectUIState,
    performEvent: (ProjectUIEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var openDatePickerDialog by remember { mutableStateOf(false) }
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (state.newTaskName.isNotEmpty()) {
                        Text(text = state.newTaskName)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { performEvent(ProjectUIEvent.ShowCreateTaskScreen) }) {
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
            ScreenSection(
                sectionTitle = "Task Name",
                content = {
                    OutlinedTextField(
                        value = state.newTaskName,
                        onValueChange = { performEvent(ProjectUIEvent.UpdateTaskName(it)) },
                        placeholder = { Text("Task Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    )
                }
            )

            ScreenSection(
                sectionTitle = "Set Deadline",
                content = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Date: ${state.newTaskDeadlineDate}")
                            IconButton(onClick = { openDatePickerDialog = true }) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                            }
                        }
                    }
                }
            )

            Button(
                enabled = state.newTaskName.isNotEmpty() && state.newTaskDeadlineTime.isNotEmpty() && state.newTaskDeadlineDate.isNotEmpty(),
                onClick = { performEvent(ProjectUIEvent.AddTask) },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Create Project")
            }

            if (state.taskCreationSuccess) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 16.dp)
                )
                Text(text = "Task created successfully")
            }
        }
    }
    if (openDatePickerDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                openDatePickerDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog = false
                        val date = dateFormatter.format(datePickerState.selectedDateMillis!!)
                        performEvent(ProjectUIEvent.UpdateTaskDeadlineDate(date))
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDatePickerDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}