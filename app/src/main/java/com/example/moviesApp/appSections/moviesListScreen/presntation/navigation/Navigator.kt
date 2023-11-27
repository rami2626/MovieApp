package com.example.moviesApp.appSections.moviesListScreen.presntation.navigation

sealed class Navigator(val route: String) {
    object MainGroup : Navigator("main_group")
    object MainScreen : Navigator("main_screen")
    object DetailsScreen : Navigator("details_screen")

    fun withArgs(vararg args: String): String {

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }

    }
}