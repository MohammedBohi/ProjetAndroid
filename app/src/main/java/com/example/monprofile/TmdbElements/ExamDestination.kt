package com.example.monprofile

import SearchTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.monprofile.Configurations.ConfigViewModel
import com.example.monprofile.Retrofit.Keyword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamDestination(navController: NavController, viewModel: ConfigViewModel) {
    val movies = viewModel.movies.collectAsState() // Observer les films
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Rechercher l'ID du mot-clé "horreur" et charger les films associés
        viewModel.searchHorrorKeyword { keywordId ->
            if (keywordId != null) {
                viewModel.getMoviesByKeyword(keywordId) // Charger les films d'horreur
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Films d'Horreur") }) },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5))
    ) { paddingValues ->
        if (movies.value.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(movies.value) { movie ->
                    MovieCard(movie, navController)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Aucun film d'horreur trouvé", color = Color.Gray)
            }
        }
    }
}