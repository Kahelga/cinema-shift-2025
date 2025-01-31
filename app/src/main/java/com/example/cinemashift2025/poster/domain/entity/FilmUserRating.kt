package com.example.cinemashift2025.poster.domain.entity

data class FilmUserRating(
    //не понимаю, нужно ли здесь делать enum class с оценками от 1 до 10
    val kinopoisk: String,
    val imdb:String
)
