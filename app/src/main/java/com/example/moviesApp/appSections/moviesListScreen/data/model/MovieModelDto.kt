package com.example.moviesApp.appSections.moviesListScreen.data.model

import com.squareup.moshi.Json

data class MovieModelDto(
    @field:Json(name = "page")
    val page: Int,
    val results: List<MovieDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)

data class MovieDto(
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String,
    @field:Json(name = "genre_ids")
    val genreIsList: List<Int>,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "poster_path")
    val poster_path: String,
    @field:Json(name = "release_date")
    val release_date: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "video")
    val video: Boolean,
    @field:Json(name = "vote_average")
    val vote_average: Double,
    @field:Json(name = "vote_count")
    val vote_count: Int
)



