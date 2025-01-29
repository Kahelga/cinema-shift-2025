package com.example.cinemashift2025.poster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmResponseModel(
    val success: Boolean,
   // val reason: String,
    val films: List<FilmModel>
)
