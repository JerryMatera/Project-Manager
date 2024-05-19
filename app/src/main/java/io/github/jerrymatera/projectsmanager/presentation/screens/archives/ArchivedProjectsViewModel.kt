package io.github.jerrymatera.projectsmanager.presentation.screens.archives

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArchivedProjectsViewModel(private val projectsRepository: ProjectsRepository) : ViewModel() {
    private val _state = MutableStateFlow(ArchivedProjectUIState())
    val state: StateFlow<ArchivedProjectUIState> = _state

    init {
        loadArchivedProjects()
    }

    private fun loadArchivedProjects() = viewModelScope.launch {
        when (val result = projectsRepository.getArchivedProjects()) {
            is NetworkResult.Error -> {
                _state.value = _state.value.copy(archiveError = result.body?.message ?: "Something went wrong")
            }
            is NetworkResult.Success -> {
                _state.value = _state.value.copy(archivedProjects = result.body.data ?: emptyList())
            }
        }
    }

}