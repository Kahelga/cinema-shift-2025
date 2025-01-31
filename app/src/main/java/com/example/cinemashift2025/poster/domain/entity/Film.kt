package com.example.cinemashift2025.poster.domain.entity

data class Film(
    val id: String,
    val name:String,
    val originalName:String,
    val description:String,
    val releaseDate :String,
    val actors:List<FilmActor>,
    val directors:List<FilmDirector>,
    val runtime: Long,
    val ageRating: AgeRatings,
    val genres:List<String>,
    val userRatings:FilmUserRating,
    val img:String,
    val country: FilmCountry
)
