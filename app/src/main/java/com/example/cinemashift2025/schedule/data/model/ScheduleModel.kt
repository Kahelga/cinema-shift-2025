package com.example.cinemashift2025.schedule.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleModel(
    val date:String,
    val seances:List<ScheduleSeancesModel>,
)
