package com.example.cinemashift2025.poster.domain.repository

import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.entity.FilmResponse

interface FilmPosterRepository {
    suspend fun getAll():FilmResponse
}