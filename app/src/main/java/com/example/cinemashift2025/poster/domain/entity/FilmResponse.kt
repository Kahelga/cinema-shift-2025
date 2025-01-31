package com.example.cinemashift2025.poster.domain.entity

data class FilmResponse(
    val success: Boolean,
    val reason: String?=null,
    val films: List<Film>
)
