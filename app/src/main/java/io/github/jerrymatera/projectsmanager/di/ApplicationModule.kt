package io.github.jerrymatera.projectsmanager.di

import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
}