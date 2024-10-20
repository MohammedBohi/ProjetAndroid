package com.example.monprofile

import SearchTopAppBar
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.monprofile.Configurations.ConfigViewModel

@Composable
fun FilmsAffichageDetails(navController: NavController, viewModel: ConfigViewModel, filmId: String) {
    val movieDetails by viewModel.moviedetails.collectAsState()
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.searchMovieDetails(filmId)
    }

    Scaffold(
        topBar = { SearchTopAppBar { query ->  } },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)) // Fond blanc cassé
    ) { paddingValues ->
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Mode paysage
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movieDetails.poster_path}"),
                    contentDescription = movieDetails.title,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = movieDetails.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Date de sortie: ${movieDetails.release_date}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Genre(s): ${movieDetails.genres.joinToString(", ") { it.name }}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Note: ${movieDetails.vote_average}/10",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        repeat((movieDetails.vote_average / 2).toInt()) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState()) // Défilement pour le synopsis
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Synopsis",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movieDetails.overview ?: "Description non disponible",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 8.dp)
                    )
                }
            }
        } else {
            // Mode portrait
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movieDetails.poster_path}"),
                    contentDescription = movieDetails.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movieDetails.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Date de sortie: ${movieDetails.release_date}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Genre(s): ${movieDetails.genres.joinToString(", ") { it.name }}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Note: ${movieDetails.vote_average}/10",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    repeat((movieDetails.vote_average / 2).toInt()) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Synopsis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movieDetails.overview ?: "Description non disponible",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
