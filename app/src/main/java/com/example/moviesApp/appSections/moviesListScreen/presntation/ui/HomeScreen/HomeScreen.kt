package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.comman.LoadingIndecatorView
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.comman.Toolbar
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.viewModel.MoviesViewModel
import com.example.moviesApp.theme.gray_light_2

@Composable
fun HomeScreen(
    viewModel: MoviesViewModel,
    navHostController: NavHostController,
    onItemClicked: (String, String) -> Unit,
    ) {
    val sharedState by viewModel.moviesState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.loadMoviesList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = gray_light_2)
    ) {
        Toolbar("MoviesApp", navController = navHostController)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp)
        ) {
            items(sharedState.items.size) { i ->
                val moviesList = sharedState.items
                val item = moviesList[i]
                LaunchedEffect(true) {
                    if (i >= moviesList.size - 1 && !sharedState.endReached && !sharedState.isLoading) {
                        viewModel.loadMoviesList()
                    }
                }

                MovieCard(item) { movieId ->
                    onItemClicked(movieId, item.title ?: "")
                }
            }
            item {
                if (sharedState.isLoading) {
                    LoadingIndecatorView()
                }
            }
        }
    }
}

