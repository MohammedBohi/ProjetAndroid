package com.example.monprofile

import SearchTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.monprofile.Configurations.ConfigViewModel
import com.example.monprofile.Retrofit.TmdbMovie
import kotlinx.coroutines.launch

@Composable
fun Films(navController: NavController, viewModel: ConfigViewModel) {
    val scope = rememberCoroutineScope()
    val movies = viewModel.movies.collectAsState()
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.getLastMovies()
    }

    Scaffold(
        topBar = {
            SearchTopAppBar { query ->
                scope.launch { viewModel.searchFilm(query) }
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)) // Fond blanc cassé

    ) { paddingValues ->
        LazyVerticalGrid(
            columns = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                GridCells.Fixed(3) // Plus de colonnes en mode paysage
            } else {
                GridCells.Fixed(2)
            },
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            items(movies.value) { movie ->
                MovieCard(movie, navController)
            }
        }
    }
}

@Composable
fun MovieCard(movie: TmdbMovie, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5)) // Blanc cassé
            .clickable { navController.navigate("filmDetails/${movie.id}") }, // Navigue vers l'écran de détails
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E) // Fond gris très foncé pour la carte
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                contentDescription = movie.title,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                fontSize = 16.sp,
                color = Color.Yellow,
                maxLines = 2
            )
            Text(
                text = movie.release_date,
                fontSize = 12.sp,
                color = Color.Yellow
            )
        }
    }
}
