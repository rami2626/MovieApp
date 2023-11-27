package com.example.moviesApp.appSections.moviesListScreen.domain.repository

import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieDto
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.networking.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    suspend fun getMovies(page:Int): Resource<List<Movie>>
     suspend fun getMovieDetails(id :Int): Flow<MovieDto>
}
