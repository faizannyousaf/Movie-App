package com.example.movieapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.MovieRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navController: NavController) {
    val movieRepository = MovieRepository()
    val getAllMovies = movieRepository.getMovies()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Movies List") },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary // Set your desired color here
            )
        )
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        )
        {
            items(items = getAllMovies) {movie ->
                movieItem(movie = movie,navController)
            }

        }
    }
}


@Composable
fun movieItem(movie: Movie, navController: NavController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer )
    ) {
        Row(

            modifier = Modifier
                .padding(8.dp)
                .clickable { navController.navigate(Screens.Detail.passID(movie.id)) }
            ,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            AsyncImage(
                model = movie.images[0], // URL from your Movie model
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)) // Rounded corners
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Title and Author
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "Author: ${movie.director}",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.titleSmall)
            }
        }
    }

}





