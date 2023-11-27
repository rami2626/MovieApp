package com.example.moviesApp.appSections.moviesListScreen.presntation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.detailsScreen.DetailsScreen
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.HomeScreen.HomeScreen
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.viewModel.MoviesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigator.MainGroup.route) {

        navigation(
            startDestination = Navigator.MainScreen.route,
            route = Navigator.MainGroup.route
        ) {

            composable(route = Navigator.MainScreen.route) {
                val viewModel = it.sharedViewModel<MoviesViewModel>(navController = navController)
                HomeScreen(
                    viewModel = viewModel,
                    navHostController = navController,
                )
                { selectedId, title ->
                    navController.navigate(Navigator.DetailsScreen.withArgs(selectedId, title))
                }
            }

            composable(
                route = Navigator.DetailsScreen.route + "/{movieId}/{movieTitle}",
                arguments = listOf(navArgument("movieId") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }, navArgument("movieTitle") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                })
            ) { entry ->
                val args = entry.arguments
                val movieId = args?.getString("movieId") ?: ""
                val viewModel =
                    entry.sharedViewModel<MoviesViewModel>(navController = navController)

                DetailsScreen(
                    movieId = movieId,
                    viewModel = viewModel,

                    navController = navController
                )

            }
        }

    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}