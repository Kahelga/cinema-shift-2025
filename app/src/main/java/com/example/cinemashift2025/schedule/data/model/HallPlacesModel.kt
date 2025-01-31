package com.example.cinemashift2025.schedule.data.model

import com.example.cinemashift2025.schedule.domain.entity.TypePlaces
import kotlinx.serialization.Serializable

@Serializable
data class HallPlacesModel(
    val price:Int,
    val type: TypePlaces
)
