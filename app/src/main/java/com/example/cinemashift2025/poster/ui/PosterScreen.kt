package com.example.cinemashift2025.poster.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cinemashift2025.R
import com.example.cinemashift2025.poster.presentation.PosterState
import com.example.cinemashift2025.poster.presentation.PosterViewModel


@Composable
fun PosterScreen(
    posterViewModel: PosterViewModel,
    onItemSelected: (filmId: String) -> Unit,
) {
    val posterState by posterViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        posterViewModel.loadFilms()
    }

    Column(modifier = Modifier
        .fillMaxSize()

    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .padding(vertical = 12.dp, horizontal = 8.dp),

            text = stringResource(id = R.string.poster_title),
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
        )

        when (val state = posterState) {
            is PosterState.Initial,
            is PosterState.Loading -> Loading()

            is PosterState.Failure -> Error(
                message = state.message ?: stringResource(id = R.string.error_unknown_error),
                onRetry = { posterViewModel.loadFilms()},
            )

            is PosterState.Content -> Content(
                films = state.films,
                onItemClicked = onItemSelected,
            )
        }
    }
}