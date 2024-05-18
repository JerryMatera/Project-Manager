package io.github.jerrymatera.projectsmanager.di

import io.github.jerrymatera.projectsmanager.data.network.repository.AuthenticationRepositoryImpl
import io.github.jerrymatera.projectsmanager.data.network.repository.ProjectsRepositoryImpl
import io.github.jerrymatera.projectsmanager.data.network.repository.TasksRepositoryImpl
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository
import io.github.jerrymatera.projectsmanager.domain.repository.ProjectsRepository
import io.github.jerrymatera.projectsmanager.domain.repository.TasksRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    single<ProjectsRepository> { ProjectsRepositoryImpl(get()) }
    single<TasksRepository> { TasksRepositoryImpl(get()) }
}