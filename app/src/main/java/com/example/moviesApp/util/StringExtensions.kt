package com.example.moviesApp.util

import java.text.SimpleDateFormat
import java.util.Locale

fun String.posterFullPath(): String {
    return "https://image.tmdb.org/t/p/w342${this}"
}

fun String.backgroundFullPath(): String {
    return "https://image.tmdb.org/t/p/original${this}"
}

fun String.parseFullDate(): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)

    val date = inputFormatter.parse(this)
    return outputFormatter.format(date!!)
}


fun String.parseDateYear(): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat("yyyy", Locale.ENGLISH)
    val date = inputFormatter.parse(this)
    return outputFormatter.format(date!!)
}