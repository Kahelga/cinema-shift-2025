package com.example.cinemashift2025.poster.domain.repository

import com.example.cinemashift2025.poster.domain.entity.Film

interface FilmPosterRepository {
    suspend fun getAll():List<Film>
}