package com.example.cinemashift2025.detailes.domain.repository

import com.example.cinemashift2025.poster.domain.entity.Film

interface DetailRepository {
    suspend fun get(filmId: String): Film
}