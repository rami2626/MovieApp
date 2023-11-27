package com.example.moviesApp.appSections.moviesListScreen.domain.useCases

import com.example.moviesApp.appSections.moviesListScreen.data.mappers.toMovie
import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieDto
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.appSections.moviesListScreen.domain.repository.MovieRepositoryInterface
import com.example.moviesApp.networking.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

interface GetMovieUseCaseInterface {
    suspend fun getMovies(page: Int): Resource<List<Movie>>

    suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDto>>
}

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepositoryInterface
) : GetMovieUseCaseInterface {

    override suspend fun getMovies(page: Int): Resource<List<Movie>> {
        return repository.getMovies(page)
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Resource<MovieDto>> = flow {
        repository.getMovieDetails(movieId)
            .map { movieDto ->
                movieDto.let {
                    val movie = it.toMovie()
                    Resource.Success(data = it)
                } ?: Resource.Error("Movie not found")
            }
            .onStart { emit(Resource.Loading()) }
            .catch { e ->
                emit(Resource.Error("Error fetching movie details: ${e.message}"))
            }
    }

}