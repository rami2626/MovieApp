package com.example.moviesApp.appSections.moviesListScreen.data.model
import com.squareup.moshi.Json

data class MovieDetailsDto(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "overview")
    val overview: String?,
    @field:Json(name = "poster_path")
    val poster_path: String?,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String?,
    @field:Json(name = "release_date")
    val release_date: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "vote_average")
    val vote_average: Double?,
)