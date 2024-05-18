package io.github.jerrymatera.projectsmanager.domain

import io.github.jerrymatera.projectsmanager.data.network.model.ArchivedStatus
import io.github.jerrymatera.projectsmanager.data.network.model.Project
import io.github.jerrymatera.projectsmanager.presentation.navigation.ProjectRoute

fun Project.toRoute(): ProjectRoute {
    return ProjectRoute(
        userUuid = userUuid,
        uuid = uuid,
        name = name,
        description = description,
        createdAt = createdAt,
        archiveTime = archivedStatus.time,
        archiveValid = archivedStatus.valid
    )
}

fun ProjectRoute.toProject(): Project {
    return Project(
        userUuid = userUuid,
        uuid = uuid,
        name = name,
        description = description,
        createdAt = createdAt,
        archivedStatus = ArchivedStatus(
            time = archiveTime,
            valid = archiveValid
        )
    )
}
