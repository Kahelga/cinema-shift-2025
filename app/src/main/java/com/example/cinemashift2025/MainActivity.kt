package com.example.cinemashift2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinemashift2025.poster.data.converter.FilmPosterConverter
import com.example.cinemashift2025.poster.data.network.FilmPosterApi
import com.example.cinemashift2025.poster.data.repository.FilmPosterRepositoryImpl
import com.example.cinemashift2025.poster.domain.repository.FilmPosterRepository
import com.example.cinemashift2025.poster.domain.usecase.GetFilmPosterUseCase
import com.example.cinemashift2025.ui.theme.CinemaShift2025Theme

class MainActivity : ComponentActivity() {

    private val networkModule = NetworkModule()

    private val filmPosterApi = networkModule.retrofit.create(FilmPosterApi::class.java)
    private val filmPosterConverter = FilmPosterConverter()
    private val filmPosterRepository: FilmPosterRepository = FilmPosterRepositoryImpl(filmPosterApi, filmPosterConverter)
    private val getFilmPosterUseCase = GetFilmPosterUseCase(filmPosterRepository)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaShift2025Theme {
                MainScreen(
                    getFilmPosterUseCase = getFilmPosterUseCase,

                )
            }
        }
    }
}