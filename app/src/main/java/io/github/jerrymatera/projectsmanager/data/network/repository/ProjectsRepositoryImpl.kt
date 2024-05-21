package io.github.jerrymatera.projectsmanager.data.network.repository

import io.github.jerrymatera.projectsmanager.data.network.ResponseHandler
import io.github.jerrymatera.projectsmanager.data.network.model.NameDetailRequest
import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository

class ProjectsRepositoryImpl(private val responseHandler: ResponseHandler) : ProjectsRepository {
    override suspend fun createProject(
        name: String,
        description: String
    ): NetworkResult<NetworkResponse<Nothing>> = responseHandler.post(
        urlPathSegments = listOf("projects"),
        body = NameDetailRequest(
            name = name,
            description = description
        )
    )

    override suspend fun getProjects(): NetworkResult<NetworkResponse<List<Project>?>> =
        responseHandler.get(
            urlPathSegments = listOf("projects")
        )

    override suspend fun getArchivedProjects(): NetworkResult<NetworkResponse<List<Project>?>> =
        responseHandler.get(
            urlPathSegments = listOf("archived-projects")
        )

    override suspend fun archiveProject(projectId: String): NetworkResult<NetworkResponse<Unit>> =
        responseHandler.delete(
            urlPathSegments = listOf("projects", projectId),
        )

    override suspend fun updateProject(
        projectId: String,
        name: String,
        description: String
    ): NetworkResult<NetworkResponse<Nothing>> = responseHandler.put(
        urlPathSegments = listOf("projects", projectId),
        body = NameDetailRequest(
            name = name,
            description = description
        )
    )

    override suspend fun unArchiveProject(projectId: String): NetworkResult<NetworkResponse<Nothing>> =
        responseHandler.put(
            urlPathSegments = listOf("unarchive-project", projectId),
        )
}