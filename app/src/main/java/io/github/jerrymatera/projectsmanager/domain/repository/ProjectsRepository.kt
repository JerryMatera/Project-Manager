package io.github.jerrymatera.projectsmanager.domain.repository

import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult

interface ProjectsRepository {
    suspend fun createProject(
        name: String,
        description: String
    ): NetworkResult<NetworkResponse<Nothing>>

    suspend fun getProjects(): NetworkResult<NetworkResponse<List<Project>?>>
    suspend fun getArchivedProjects(): NetworkResult<NetworkResponse<List<Project>?>>
    suspend fun archiveProject(projectId: String): NetworkResult<NetworkResponse<Nothing>>
    suspend fun updateProject(
        projectId: String,
        name: String,
        description: String
    ): NetworkResult<NetworkResponse<Nothing>>

    suspend fun unArchiveProject(projectId: String): NetworkResult<NetworkResponse<Nothing>>

}