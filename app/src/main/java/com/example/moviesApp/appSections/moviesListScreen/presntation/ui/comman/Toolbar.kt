@file:JvmName("LoadingViewKt")

package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.comman

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesApp.theme.gray_light_2

@Composable
fun Toolbar(
    title: String? = "",
    navController: NavController,
    hasBackNavigation: Boolean? = false
) {
    TopAppBar(
        title = {
            Text(
                text = title ?: "",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (hasBackNavigation == true) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        backgroundColor = gray_light_2,
        elevation = 0.dp
    )
}
