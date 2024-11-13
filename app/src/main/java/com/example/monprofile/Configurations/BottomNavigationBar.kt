package com.example.monprofile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF000000) // Noir
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Movie, contentDescription = "Films", tint = Color(0xFFFFD700)) }, // Icône dorée
            label = { Text("Films", color = Color(0xFFFFD700)) }, // Texte doré
            selected = false,
            onClick = { navController.navigate("films") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Tv, contentDescription = "Séries", tint = Color(0xFFFFD700)) },
            label = { Text("Séries", color = Color(0xFFFFD700)) },
            selected = false,
            onClick = { navController.navigate("series") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Acteurs", tint = Color(0xFFFFD700)) },
            label = { Text("Acteurs", color = Color(0xFFFFD700)) },
            selected = false,
            onClick = { navController.navigate("actors") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.HomeMax, contentDescription = "Exam", tint = Color(0xFFFFD700)) },
            label = { Text("Vide", color = Color(0xFFFFD700)) },
            selected = false,
            onClick = { navController.navigate("exam") } // Naviguer vers "empty"
        )
    }
}