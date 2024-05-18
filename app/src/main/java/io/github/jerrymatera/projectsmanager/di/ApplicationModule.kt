package io.github.jerrymatera.projectsmanager.di

import io.github.jerrymatera.projectsmanager.data.preferences.UserPrefsStore
import io.github.jerrymatera.projectsmanager.data.preferences.UserPrefsStoreImpl
import io.github.jerrymatera.projectsmanager.presentation.screens.home.HomeViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.project.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProjectViewModel)
    single<UserPrefsStore> { UserPrefsStoreImpl(get()) }
}
