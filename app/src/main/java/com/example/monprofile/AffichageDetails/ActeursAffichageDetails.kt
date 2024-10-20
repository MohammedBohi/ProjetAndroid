package com.example.monprofile

import SearchTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActeursAffichageDetails(navController: NavController, viewModel: ConfigViewModel, actorId: String) {
    val actorDetails by viewModel.actorToLook.collectAsState()
    val configuration = LocalConfiguration.current

    // Chargement des détails de l'acteur au démarrage
    LaunchedEffect(Unit) {
        viewModel.searchActorDetails(actorId)
    }

    Scaffold(
        topBar = {
            SearchTopAppBar { query ->
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.background(Color(0xFFF5F5F5)) // Fond blanc cassé
    ) { paddingValues ->
        if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            // Affichage paysage
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actorDetails.profile_path}"),
                    contentDescription = actorDetails.name,
                    modifier = Modifier
                        .weight(1f)
                        .height(300.dp)
                        .padding(end = 16.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = actorDetails.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Popularité: ${actorDetails.popularity}",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        } else {
            // Affichage portrait
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actorDetails.profile_path}"),
                    contentDescription = actorDetails.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = actorDetails.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Popularité: ${actorDetails.popularity}",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
