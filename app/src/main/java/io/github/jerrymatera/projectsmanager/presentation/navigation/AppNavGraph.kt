package io.github.jerrymatera.projectsmanager.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.jerrymatera.projectsmanager.presentation.screens.home.HomeScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginScreen
import io.github.jerrymatera.projectsmanager.presentation.screens.login.LoginViewModel
import io.github.jerrymatera.projectsmanager.presentation.screens.register.RegisterScreen
import org.koin.compose.koinInject

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoutes.Login.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = ScreenRoutes.Login.route) {
            val viewModel: LoginViewModel = koinInject<LoginViewModel>()
            val state = viewModel.state.collectAsState()
            LoginScreen(
                state = state.value,
                performEvent = viewModel::performEvent,
                navHostController
            )
        }
        composable(route = ScreenRoutes.Register.route) {
            RegisterScreen()
        }
        composable(route = ScreenRoutes.Home.route) {
            HomeScreen()
        }
    }
}