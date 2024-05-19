package io.github.jerrymatera.projectsmanager.presentation.screens.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val tasksRepository: TasksRepository,
    private val project: Project,
) : ViewModel() {
    private val _state = MutableStateFlow(ProjectUIState(project = project))
    val state: StateFlow<ProjectUIState> = _state

    init {
        performEvent(ProjectUIEvent.GetProjectTasks)
        performEvent(ProjectUIEvent.GetProjectArchivedTasks)
    }

    fun performEvent(event: ProjectUIEvent) {
        when (event) {
            is ProjectUIEvent.AddTask -> addTask()
            is ProjectUIEvent.GetProjectTasks -> getProjectTasks()
            is ProjectUIEvent.UpdateTaskName -> {
                _state.value = _state.value.copy(newTaskName = event.name)
            }

            is ProjectUIEvent.ShowCreateTaskScreen -> {
                _state.value = _state.value.copy(createTask = !_state.value.createTask)
            }

            is ProjectUIEvent.UpdateTaskDeadlineDate -> {
                _state.value = _state.value.copy(newTaskDeadlineDate = event.date)
            }

            is ProjectUIEvent.GetProjectArchivedTasks -> getArchivedTasks()
        }
    }

    private fun getProjectTasks() = viewModelScope.launch {
        when (val result = tasksRepository.getProjectTasks(projectId = project.uuid)) {
            is NetworkResult.Error -> {
                _state.value = _state.value.copy(
                    projectTasksError = result.body?.message ?: "Something went wrong"
                )
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(projectTasks = result.body.data ?: emptyList())
            }
        }
    }

    private fun addTask() = viewModelScope.launch {
        val result = tasksRepository.createTask(
            projectId = _state.value.project!!.uuid,
            name = _state.value.newTaskName,
            deadline = _state.value.newTaskDeadlineDate
        )
        when (result) {
            is NetworkResult.Error -> {
                _state.value = _state.value.copy(
                    taskCreationError = result.body?.message ?: "Something went wrong"
                )
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(taskCreationSuccess = true)
                performEvent(ProjectUIEvent.GetProjectTasks)
            }
        }
    }

    private fun getArchivedTasks() = viewModelScope.launch {
        when (val result = tasksRepository.getArchivedProjectTasks(projectId = project.uuid)) {
            is NetworkResult.Error -> {}
            is NetworkResult.Success -> {
                _state.value = _state.value.copy(archivedTasks = result.body.data ?: emptyList())
            }
        }
    }
}