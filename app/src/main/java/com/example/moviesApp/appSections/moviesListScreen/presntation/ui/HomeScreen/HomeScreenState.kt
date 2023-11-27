package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.HomeScreen
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie

data class HomeScreenState(
    val isLoading: Boolean = false,
    val items: List<Movie> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)