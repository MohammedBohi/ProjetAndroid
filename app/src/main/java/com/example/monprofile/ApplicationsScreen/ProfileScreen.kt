package com.example.monprofile
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.example.monprofile.R
@Composable
fun ProfileScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeLayout(navController)
    } else {
        VerticalLayout(navController)
    }
}

@Composable
fun VerticalLayout(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProfileImage()

        Spacer(modifier = Modifier.height(12.dp))

        // Nom et profession
        Text(
            text = "Mohammed Bohi",
            fontSize = 26.sp,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Étudiant à ISIS",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactInfo()

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("films") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Démarrer")
        }
    }
}

@Composable
fun LandscapeLayout(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            ProfileImage()

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Mohammed Bohi",
                fontSize = 26.sp,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "Étudiant à ISIS",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Colonne droite avec les infos de contact en haut et le bouton en bas
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactInfo()

            Spacer(modifier = Modifier.height(20.dp))

            // Bouton centré en bas de la colonne
            Button(
                onClick = { navController.navigate("films") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Démarrer")
            }
        }
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.linkedin),
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mohammed Bohi",
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.gmail),
                contentDescription = "Gmail Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "mohamedbohi2001@gmail.com",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.linkedin_picture),
        contentDescription = "Ma photo",
        modifier = Modifier
            .size(250.dp)
            .clip(CircleShape)
            .border(4.dp, Color.Gray, CircleShape)
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}
