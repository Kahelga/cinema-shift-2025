package com.example.cinemashift2025.detailes.presentation

import com.example.cinemashift2025.poster.domain.entity.Film

interface DetailsState {
    data object Initial : DetailsState
    data object Loading : DetailsState
    data class Failure(val message: String?) : DetailsState
    data class Content(val film: Film) : DetailsState
}