package com.example.cinemashift2025.poster.data.model

import com.example.cinemashift2025.poster.domain.entity.AgeRatings
import kotlinx.serialization.Serializable

@Serializable
data class FilmModel(
    val id: String,
    val name:String,
    val originalName:String,
    val description:String,
    val releaseDate :String,
    val actors:List<FilmActorModel>,
    val directors:List<FilmDirectorModel>,
    val runtime: Long,
    val ageRating: AgeRatings,
    val genres:List<String>,
    val userRatings: FilmUserRatingModel,
    val img:String,
    val country: FilmCountryModel
)
