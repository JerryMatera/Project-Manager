package io.github.jerrymatera.projectsmanager.presentation.screens.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val projectsRepository: ProjectsRepository,
    val tasksRepository: TasksRepository,
    val projectId: String
) : ViewModel() {
    private val _state = MutableStateFlow(ProjectUIState())
    val state: StateFlow<ProjectUIState> = _state

    init {
        performEvent(ProjectUIEvent.GetProject)
        performEvent(ProjectUIEvent.GetProjectTasks)
    }

    fun performEvent(event: ProjectUIEvent) {
        when (event) {
            is ProjectUIEvent.AddTask -> addTask()
            is ProjectUIEvent.GetProject -> getProject()
            is ProjectUIEvent.GetProjectTasks -> getProjectTasks()
        }
    }

    private fun getProjectTasks() = viewModelScope.launch {
        when (val result = tasksRepository.getProjectTasks(projectId = projectId)) {
            is NetworkResult.Error -> {
                _state.value = _state.value.copy(
                    projectTasksError = result.body?.message ?: "Something went wrong"
                )
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(projectTasks = result.body.data)
            }
        }
    }

    private fun getProject() = viewModelScope.launch {
        when(val result = projectsRepository.getProjects())
    }

    private fun addTask() {
        TODO("Not yet implemented")
    }
}