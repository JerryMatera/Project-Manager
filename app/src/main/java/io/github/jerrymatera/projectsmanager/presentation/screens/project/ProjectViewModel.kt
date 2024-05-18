package io.github.jerrymatera.projectsmanager.presentation.screens.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val projectsRepository: ProjectsRepository,
    private val tasksRepository: TasksRepository,
    private val project: Project,
) : ViewModel() {
    private val _state = MutableStateFlow(ProjectUIState(project = project))
    val state: StateFlow<ProjectUIState> = _state

    init {
        performEvent(ProjectUIEvent.GetProjectTasks)
    }

    fun performEvent(event: ProjectUIEvent) {
        when (event) {
            is ProjectUIEvent.AddTask -> addTask()
            is ProjectUIEvent.GetProjectTasks -> getProjectTasks()
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

    private fun addTask() {
        TODO("Not yet implemented")
    }
}