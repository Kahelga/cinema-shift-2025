package com.example.cinemashift2025.detailes.data.model

import com.example.cinemashift2025.poster.data.model.FilmModel
import kotlinx.serialization.Serializable

@Serializable
data class DetailsModel(
    val success: Boolean,
    val reason: String?=null,
    val film: FilmModel
)
