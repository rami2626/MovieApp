package com.example.moviesApp.appSections.moviesListScreen.data.mappers

import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieDto
import com.example.moviesApp.appSections.moviesListScreen.data.model.MovieModelDto
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.util.parseFullDate
import com.example.moviesApp.util.posterFullPath


fun MovieModelDto.toMovies(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            title = it.title,
            photoUrl = it.poster_path.posterFullPath(),
            publishedAt = it.release_date.parseFullDate(),
            rate = it.vote_average
        )
    }
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        photoUrl = backdrop_path.posterFullPath(),
        publishedAt = release_date,
        rate = vote_average
    )
}
