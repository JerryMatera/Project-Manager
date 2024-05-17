package io.github.jerrymatera.projectsmanager.di

import io.github.jerrymatera.projectsmanager.data.network.repository.AuthenticationRepositoryImpl
import io.github.jerrymatera.projectsmanager.domain.repository.AuthenticationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}