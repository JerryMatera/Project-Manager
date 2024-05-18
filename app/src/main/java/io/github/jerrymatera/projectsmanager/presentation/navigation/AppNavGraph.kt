package io.github.jerrymatera.projectsmanager.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.jerrymatera.projectsmanager.domain.toProject
import io.github.jerrymatera.projectsmanager.presentation.screens.archives.ArchivedProjectsScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.home.HomeScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.home.HomeViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.project.ProjectScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.project.ProjectViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.register.RegisterScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Login,
        modifier = modifier.fillMaxSize()
    ) {
        composable<Login> {
            val viewModel: LoginViewModel = koinInject<LoginViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            LoginScreen(
                state = state.value,
                performEvent = viewModel::performEvent,
                navHostController
            )
        }
        composable<Register> {
            RegisterScreen()
        }
        composable<Home> {
            val viewModel: HomeViewModel = koinViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle()
            HomeScreen(
                state = state.value,
                performEvent = viewModel::performEvent,
                navHostController
            )
        }
        composable<Archives> {
            ArchivedProjectsScreen()
        }
        composable<ProjectRoute> {
            val project = it.toRoute<ProjectRoute>()
            val viewModel: ProjectViewModel = koinViewModel(
                parameters = { parametersOf(project.toProject()) }
            )
            val state = viewModel.state.collectAsStateWithLifecycle()
            ProjectScreen(
                state = state.value,
                performEvent = viewModel::performEvent,
                navHostController
            )
        }
    }
}