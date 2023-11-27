package com.example.moviesApp.appSections.moviesListScreen.data.repository

import com.example.moviesApp.appSections.moviesListScreen.data.dataSources.MovieDataSourceInterface
import com.example.moviesApp.appSections.moviesListScreen.data.mappers.toMovies
import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieDto
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.appSections.moviesListScreen.domain.repository.MovieRepositoryInterface
import com.example.moviesApp.networking.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieDataSource: MovieDataSourceInterface) :
    MovieRepositoryInterface {

    override suspend fun getMovies(page: Int): Resource<List<Movie>> {
        return try {
            Resource.Success(data = movieDataSource.getMovies(page).toMovies())
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "error")
        }
    }

    override suspend fun getMovieDetails(id: Int): Flow<MovieDto> {
        return  movieDataSource.getMovieDetails(id)
    }
}