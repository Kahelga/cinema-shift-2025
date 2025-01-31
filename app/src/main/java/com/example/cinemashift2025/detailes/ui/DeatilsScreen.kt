package com.example.cinemashift2025.detailes.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinemashift2025.R
import com.example.cinemashift2025.detailes.presentation.DetailsState
import com.example.cinemashift2025.detailes.presentation.DetailsViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
) {
    val detailsState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFilm()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            text = stringResource(id = R.string.details_title),
            style = MaterialTheme.typography.displaySmall,
        )

        when (val state = detailsState) {
            is DetailsState.Initial,
            is DetailsState.Loading -> Loading()

            is DetailsState.Failure -> Error(
                message = state.message ?: stringResource(id = R.string.error_unknown_error),
                onRetry = {
                    viewModel.loadFilm()
                }
            )

            is DetailsState.Content -> Content(
                film = state.film,
                scheduleResponse = state.scheduleResponse
            )
        }
    }
}