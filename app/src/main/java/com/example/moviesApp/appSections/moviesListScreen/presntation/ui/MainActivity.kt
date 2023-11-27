package com.example.moviesApp.appSections.moviesListScreen.presntation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesApp.appSections.moviesListScreen.presntation.navigation.Navigation
import com.example.moviesApp.theme.MyWeatherappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherappTheme {
                   Navigation()
                }
            }
        }
}