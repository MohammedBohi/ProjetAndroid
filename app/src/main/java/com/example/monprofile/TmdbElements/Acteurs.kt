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
import com.example.monprofile.Retrofit.TmdbActor
import kotlinx.coroutines.launch

@Composable
fun Acteurs(navController: NavController, viewModel: ConfigViewModel) {
    val scope = rememberCoroutineScope()
    val actors = viewModel.actors.collectAsState()
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.getLastActors()
    }

    Scaffold(
        topBar = {
            SearchTopAppBar { query ->
                scope.launch { viewModel.searchActor(query) }
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
            items(actors.value) { actor ->
                ActorCard(actor, navController, configuration.orientation)
            }
        }
    }
}

@Composable
fun ActorCard(actor: TmdbActor, navController: NavController, orientation: Int) {
    Card(
        modifier = Modifier
            .background(Color(0xFFF5F5F5)) // Blanc cassé
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("actorDetails/${actor.id}") }, // Navigue vers l'écran de détails
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E) // Fond gris très foncé
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actor.profile_path}"),
                    contentDescription = actor.name,
                    modifier = Modifier
                        .height(120.dp)
                        .width(90.dp)
                        .padding(end = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = actor.name,
                        fontSize = 18.sp,
                        color = Color.Yellow,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Popularité: ${actor.popularity}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        } else {
            // Mode portrait
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actor.profile_path}"),
                    contentDescription = actor.name,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = actor.name,
                    fontSize = 16.sp,
                    color = Color.Yellow,
                    maxLines = 2
                )
                Text(
                    text = "Popularité: ${actor.popularity}",
                    fontSize = 12.sp,
                    color = Color.Yellow
                )
            }
        }
    }
}
