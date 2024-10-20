import androidx.compose.material3.SmallTopAppBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(onSearch: (String) -> Unit) {
    val searchQuery = remember { mutableStateOf("") }

    TopAppBar(
        title = {
            BasicTextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    onSearch(it)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                        Spacer(modifier = Modifier.width(8.dp))
                        if (searchQuery.value.isEmpty()) {
                            Text(text = "Rechercher...", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
        },
        colors = topAppBarColors(
            containerColor = Color.Black,        // Couleur de fond de la barre
            titleContentColor = Color.Yellow,    // Couleur du texte du titre
            navigationIconContentColor = Color.Yellow, // Couleur des icônes de navigation
            actionIconContentColor = Color.Yellow // Couleur des icônes d'action
        )
    )
}
