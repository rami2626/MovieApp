package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.comman.LoadingIndicatorView
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.comman.Toolbar
import com.example.moviesApp.appSections.moviesListScreen.presntation.ui.viewModel.MoviesViewModel
import com.example.moviesApp.theme.*

@Composable
fun DetailsScreen(
    movieId: String,
    viewModel: MoviesViewModel,
    navController: NavHostController,
) {
    val state by viewModel.movieDetailsSates.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = gray_light_2)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = gray_light_2)
        ) {
            Toolbar(navController = navController, hasBackNavigation = true)
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LoadingIndicatorView()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Row(
                        modifier = Modifier
                            .background(color = gray_light_1)
                            .fillMaxWidth()

                    ) {
                        AsyncImage(
                            model = state.item?.photoUrl,
                            contentDescription = "The delasign logo",
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = state.item?.title ?: "",
                        fontSize = 24.sp,
                        color = Color.White,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "(${state.item?.publishedAt})",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star Icon",
                            tint = yellow_dark,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = (state.item?.rate ?: 0.0).toString(),
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Description",
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = state.item?.description ?: "",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadMovieDetails(movieId.toInt())
    }
}

