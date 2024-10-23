package com.example.movieapp.ui.theme


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.repository.MovieRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen (navController: NavController, movieId : String) {
    val movieRepository = MovieRepository()
    val getAllMovies = movieRepository.getMovies()
    val selectedMovie = getAllMovies.find { it ->
        it.id == movieId
    }
        ?: throw IllegalStateException("Movie with ID $movieId not found")
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = selectedMovie.title) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary // Set your desired color here
            ),
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        )
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Movie images",
                color = Color.Black,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
            LazyRow {
                items(selectedMovie.images) { image ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(240.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        AsyncImage(
                            model = image, contentDescription = "Movie Poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(12.dp)
                                .size(240.dp)
                        )
                    }
                }
            }

            Text(
                text = "Plot: ${selectedMovie.plot}",
                color = Color.Black,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
          //  Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Release Year: ${selectedMovie.year}",
                color = Color.Black,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
            //Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Rating: ${selectedMovie.rating}",
                color = Color.Black,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

    }


}


