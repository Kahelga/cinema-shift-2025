package com.example.cinemashift2025.poster.presentation

import com.example.cinemashift2025.poster.domain.entity.FilmResponse

sealed interface PosterState {
    data object Initial:PosterState
    data object Loading:PosterState
    data class Failure(val message: String?) : PosterState
    data class Content(val films: FilmResponse) : PosterState
}