package com.example.cinemashift2025.poster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmCountryModel(
    val name: String,
    val code: String,
    val code2: String,
    val id: Int
)
