package com.example.cinemashift2025.schedule.data.model

import com.example.cinemashift2025.schedule.domain.entity.HallPlaces
import kotlinx.serialization.Serializable

@Serializable
data class SeancesHallModel(
     val name:String,
     val places:List<List<HallPlacesModel>>
)
