package io.github.jerrymatera.projectsmanager.domain.repository

import io.github.jerrymatera.projectsmanager.data.network.model.NetworkResponse
import io.github.jerrymatera.projectsmanager.data.network.model.Task
import io.github.jerrymatera.projectsmanager.data.utils.NetworkResult

interface TasksRepository {
    suspend fun createTask(
        projectId: String,
        name: String,
        deadline: String
    ): NetworkResult<NetworkResponse<Nothing>>

    suspend fun getAllTasks(): NetworkResult<NetworkResponse<List<Task>?>>
    suspend fun getProjectTasks(projectId: String): NetworkResult<NetworkResponse<List<Task>?>>
    suspend fun getArchivedProjectTasks(projectId: String): NetworkResult<NetworkResponse<List<Task>?>>

    suspend fun updateProjectTask(
        taskId: String,
        name: String,
        deadline: String
    ): NetworkResult<NetworkResponse<Nothing>>

    suspend fun archiveProjectTask(taskId: String): NetworkResult<NetworkResponse<Nothing>>
    suspend fun unArchiveProjectTask(taskId: String): NetworkResult<NetworkResponse<Nothing>>

}