package com.example.cinemashift2025.schedule.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleSeancesModel(
     val time:String,
     val hall: SeancesHallModel
)
