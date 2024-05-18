package io.github.jerrymatera.projectsmanager.data.network.repository

import io.github.jerrymatera.projectsmanager.data.network.ResponseHandler
import io.github.jerrymatera.projectsmanager.data.network.model.CreateTaskRequest
import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.Task
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository

class TasksRepositoryImpl(private val responseHandler: ResponseHandler) : TasksRepository {
    override suspend fun createTask(
        projectId: String,
        name: String,
        deadline: String
    ): NetworkResult<NetworkResponse<Nothing>> = responseHandler.post(
        urlPathSegments = listOf("project-tasks"),
        body = CreateTaskRequest(
            projectUuid = projectId,
            name = name,
            deadline = deadline
        )
    )

    override suspend fun getAllTasks(): NetworkResult<NetworkResponse<List<Task>?>> =
        responseHandler.get(urlPathSegments = listOf("all-project-tasks"))

    override suspend fun getProjectTasks(projectId: String): NetworkResult<NetworkResponse<List<Task>?>> =
        responseHandler.get(
            urlPathSegments = listOf("project-tasks", projectId)
        )

    override suspend fun getArchivedProjectTasks(projectId: String): NetworkResult<NetworkResponse<List<Task>?>> =
        responseHandler.get(urlPathSegments = listOf("archived-project-tasks", projectId))

    override suspend fun updateProjectTask(
        taskId: String,
        name: String,
        deadline: String
    ): NetworkResult<NetworkResponse<Nothing>> = responseHandler.put(
        urlPathSegments = listOf("project-tasks", taskId),
        body = CreateTaskRequest(
            name = name,
            deadline = deadline
        )
    )

    override suspend fun archiveProjectTask(taskId: String): NetworkResult<NetworkResponse<Nothing>> =
        TODO()

    override suspend fun unArchiveProjectTask(taskId: String): NetworkResult<NetworkResponse<Nothing>> =
        TODO()
}