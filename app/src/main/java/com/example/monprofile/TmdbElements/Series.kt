package com.example.monprofile

import SearchTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.monprofile.Retrofit.TmdbSerie
import kotlinx.coroutines.launch

@Composable
fun Series(navController: NavController, viewModel: ConfigViewModel) {
    val scope = rememberCoroutineScope()
    val series = viewModel.series.collectAsState()
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.getLastSeries()
    }

    Scaffold(
        topBar = {
            SearchTopAppBar { query ->
                scope.launch { viewModel.searchSerie(query) }
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)) // Fond blanc cassé

    ) { paddingValues ->
        LazyVerticalGrid(
            columns = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
                GridCells.Fixed(3) // Affiche 3 colonnes en mode paysage
            } else {
                GridCells.Fixed(2)
            },
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            items(series.value) { serie ->
                SeriesCard(serie, navController, configuration.orientation)
            }
        }
    }
}

@Composable
fun SeriesCard(serie: TmdbSerie, navController: NavController, orientation: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("seriesDetails/${serie.id}") }, // Navigue vers l'écran de détails
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E) // Fond gris très foncé
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            Row( // Mode paysage : image à gauche et détails à droite
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${serie.poster_path}"),
                    contentDescription = serie.name,
                    modifier = Modifier
                        .height(120.dp)
                        .width(90.dp) // Ajustement de la taille en paysage
                        .padding(end = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = serie.name,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Date de sortie : ${serie.first_air_date}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        } else {
            // Mode portrait : image en haut et détails en dessous
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${serie.poster_path}"),
                    contentDescription = serie.name,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = serie.name,
                    fontSize = 16.sp,
                    color = Color.Yellow,
                    maxLines = 2
                )
                Text(
                    text = serie.first_air_date,
                    fontSize = 12.sp,
                    color = Color.Yellow
                )
            }
        }
    }
}
