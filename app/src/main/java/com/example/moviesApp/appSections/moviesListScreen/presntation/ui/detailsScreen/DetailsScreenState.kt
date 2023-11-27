package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.detailsScreen

import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie

data class DetailsScreenState(
    val isLoading: Boolean = true,
    val item: Movie? = null,
    val error: String? = null,
)
