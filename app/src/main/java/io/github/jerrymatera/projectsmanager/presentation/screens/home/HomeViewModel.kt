package io.github.jerrymatera.projectsmanager.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val projectsRepository: ProjectsRepository,
    private val tasksRepository: TasksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        performEvent(HomeEvent.GetUser)
        performEvent(HomeEvent.GetProjects)
        performEvent(HomeEvent.GetAllTasks)
    }

    fun performEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetUser -> getUser()
            is HomeEvent.GetProjects -> getProjects()
            is HomeEvent.GetAllTasks -> getAllTasks()
        }
    }

    private fun getUser() = viewModelScope.launch {
        when (val result = authenticationRepository.currentUser()) {
            is NetworkResult.Error -> {
                _state.value =
                    _state.value.copy(userError = result.body?.message ?: "Something went wrong")
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(user = result.body.data)
            }
        }
    }

    private fun getProjects() = viewModelScope.launch {
        when (val result = projectsRepository.getProjects()) {
            is NetworkResult.Error -> {
                _state.value = _state.value.copy(
                    projectsError = result.body?.message ?: "Something went wrong"
                )
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(projects = result.body.data)
            }
        }
    }

    private fun getAllTasks() = viewModelScope.launch {
        when (val result = tasksRepository.getAllTasks()) {
            is NetworkResult.Error -> {
                _state.value =
                    _state.value.copy(tasksError = result.body?.message ?: "Something went wrong")
            }

            is NetworkResult.Success -> {
                _state.value = _state.value.copy(tasks = result.body.data)
            }
        }
    }
}