package com.example.cinemashift2025

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cinemashift2025.detailes.DetailsRoute
import com.example.cinemashift2025.detailes.domain.usecase.GetFilmUseCase
import com.example.cinemashift2025.detailes.presentation.DetailsViewModel
import com.example.cinemashift2025.detailes.presentation.DetailsViewModelFactory
import com.example.cinemashift2025.detailes.ui.DetailsScreen
import com.example.cinemashift2025.poster.PosterRoute
import com.example.cinemashift2025.poster.domain.usecase.GetFilmPosterUseCase
import com.example.cinemashift2025.poster.presentation.PosterViewModel
import com.example.cinemashift2025.poster.presentation.PosterViewModelFactory
import com.example.cinemashift2025.poster.ui.PosterScreen
import com.example.cinemashift2025.schedule.domain.usecase.GetScheduleUseCase

@Composable
fun MainScreen(
    getFilmPosterUseCase: GetFilmPosterUseCase,
    getFilmUseCase: GetFilmUseCase,
    getScheduleUseCase: GetScheduleUseCase

) {
    val navController = rememberNavController()
    Surface {
        NavHost(navController = navController, startDestination = PosterRoute) {
            composable<PosterRoute> {
                val viewModel: PosterViewModel = viewModel(factory = PosterViewModelFactory(getFilmPosterUseCase))
                PosterScreen(
                    viewModel,
                    onItemSelected = {navController.navigate(DetailsRoute(filmId = it)) },//переход на детали
                )
            }
            composable<DetailsRoute> {
                val destination = it.toRoute<DetailsRoute>()
                val viewModel = viewModel(DetailsViewModel::class.java, factory = DetailsViewModelFactory(destination.filmId, getFilmUseCase,getScheduleUseCase))
                DetailsScreen(
                    viewModel
                )
            }
        }
    }
}