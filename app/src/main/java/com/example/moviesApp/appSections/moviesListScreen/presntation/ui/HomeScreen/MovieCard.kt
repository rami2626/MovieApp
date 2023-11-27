package com.example.moviesApp.appSections.moviesListScreen.presntation.ui.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesApp.appSections.moviesListScreen.domain.entity.Movie
import com.example.moviesApp.theme.gray_light_3
import com.example.moviesApp.theme.yellow_dark

@Composable
fun MovieCard(item: Movie, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable {
                onItemClicked(item.id.toString())
            }, shape = RoundedCornerShape(8.dp), elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = gray_light_3)
        ) {
            Box(
                modifier = Modifier
                    .heightIn(max = 150.dp)
                    .widthIn(max = 110.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.padding(8.dp),
                    model = item.photoUrl,
                    contentDescription = "The delasign logo",
                    contentScale = ContentScale.FillBounds,
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.title ?: "",
                    fontSize = 20.sp,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.publishedAt ?: "-", fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = yellow_dark,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = item.rate.toString(), fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}
