package com.example.cinemashift2025.poster.data.model

import com.example.cinemashift2025.poster.domain.entity.AgeRatings
import kotlinx.serialization.Serializable

@Serializable
data class FilmModel(
    //Надо ли делать @SerialName, если названия совпадают?
    val id: String,
    val name:String,
    val originalNam:String,
    val description:String,
    val releaseDate :String,
    val actors:List<FilmActorModel>,
    val directors:List<FilmDirectorModel>,
    val runtime: Int,
    val ageRatings: AgeRatings,
    val genres:List<String>,
    val userRatings: FilmUserRatingModel,
    val img:String,
    val country: FilmCountryModel
)
