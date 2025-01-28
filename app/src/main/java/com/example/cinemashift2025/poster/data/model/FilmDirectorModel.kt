package com.example.cinemashift2025.poster.data.model

import com.example.cinemashift2025.poster.domain.entity.Professions
import kotlinx.serialization.Serializable

@Serializable
data class FilmDirectorModel(
    val id:String,
    val professions: List<Professions>,
    val fullName:String
)
