
package com.example.monprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.monprofile.Configurations.ConfigViewModel
import com.example.monprofile.Retrofit.TmdbMovie

@Composable
fun Films(viewModel: ConfigViewModel) {
    // Observer l'état des films dans le ViewModel
    val movies by viewModel.movies.collectAsState()

    // Si la liste des films est vide, déclenche un chargement initial
    if (movies.isEmpty()) {
        viewModel.getLastMovies()
    }

    // Affichage de la liste des films dans une grille
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: TmdbMovie) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Charger l'image de l'affiche avec Coil
        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        Image(
            painter = rememberAsyncImagePainter(posterUrl),
            contentDescription = "Affiche de ${movie.title}",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Afficher le titre du film
        Text(text = movie.title)
    }
}