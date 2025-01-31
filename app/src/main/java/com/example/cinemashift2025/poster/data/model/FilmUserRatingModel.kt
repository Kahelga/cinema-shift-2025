package com.example.cinemashift2025.poster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmUserRatingModel(
    val kinopoisk: String,
    val imdb:String
)
