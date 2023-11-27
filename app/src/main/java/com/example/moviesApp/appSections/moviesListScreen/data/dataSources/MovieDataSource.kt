package com.example.moviesApp.appSections.moviesListScreen.data.dataSources

import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieDto
import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieModelDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieApi {
    @GET("/3/discover/movie?api_key=594511549fb79caa0585e0097ca41aa9")
    suspend fun getMovies(
        @Query("page") page: Int,
    ): MovieModelDto

    @GET("/3/movie/{id}?api_key=594511549fb79caa0585e0097ca41aa9")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): Flow<MovieDto>
}

interface MovieDataSourceInterface {
    suspend fun getMovies(
        page: Int
    ): MovieModelDto

    suspend fun getMovieDetails(
        movieId: Int
    ): Flow<MovieDto>
}

class MovieDataSource @Inject constructor(retrofit: Retrofit) :
    MovieDataSourceInterface {
    private val api = retrofit.create(GetMovieApi::class.java)

    override suspend fun getMovies(page: Int): MovieModelDto {
        return api.getMovies(page)
    }

    override suspend fun getMovieDetails(id: Int): Flow<MovieDto> = flow {
        api.getMovieDetails(id)
    }
}