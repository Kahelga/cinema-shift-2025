package com.example.cinemashift2025

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinemashift2025.poster.PosterRoute
import com.example.cinemashift2025.poster.domain.usecase.GetFilmPosterUseCase
import com.example.cinemashift2025.poster.presentation.PosterViewModel
import com.example.cinemashift2025.poster.presentation.PosterViewModelFactory
import com.example.cinemashift2025.poster.ui.PosterScreen

@Composable
fun MainScreen(
    getFilmPosterUseCase: GetFilmPosterUseCase

) {
    val navController = rememberNavController()
    Surface {
        NavHost(navController = navController, startDestination = PosterRoute) {
            composable<PosterRoute> {
                val viewModel: PosterViewModel = viewModel(factory = PosterViewModelFactory(getFilmPosterUseCase))
                PosterScreen(
                    viewModel,
                    onItemSelected = {},//переход на детали
                )
            }
        }
    }
}