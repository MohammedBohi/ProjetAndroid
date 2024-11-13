package com.example.monprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monprofile.ui.theme.MonProfileTheme
import com.example.monprofile.Configurations.ConfigViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonProfileTheme {
                val navController = rememberNavController()
                val viewModel = ConfigViewModel()  // Création de l'instance de ConfigViewModel
                AppNavHost(navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, viewModel: ConfigViewModel) {
    NavHost(
        navController = navController,
        startDestination = "profile"  // Définit l'écran de départ
    ) {
        composable("profile") { ProfileScreen(navController) }
        composable("films") { Films(navController, viewModel) }
        composable("series") { Series(navController, viewModel) }
        composable("actors") { Acteurs(navController, viewModel) }
        composable("exam") { ExamDestination(navController, viewModel) } // Nouvelle destination ajoutée

        // Écrans de détails pour les films, séries et acteurs
        composable("filmDetails/{filmId}") { backStackEntry ->
            val filmId = backStackEntry.arguments?.getString("filmId") ?: ""
            FilmsAffichageDetails(navController, viewModel, filmId)
        }
        composable("seriesDetails/{seriesId}") { backStackEntry ->
            val seriesId = backStackEntry.arguments?.getString("seriesId") ?: ""
            SeriesAffichageDetails(navController, viewModel, seriesId)
        }
        composable("actorDetails/{actorId}") { backStackEntry ->
            val actorId = backStackEntry.arguments?.getString("actorId") ?: ""
            ActeursAffichageDetails(navController, viewModel, actorId)
        }
    }
}