package com.example.cinemashift2025.schedule.data.model


import kotlinx.serialization.Serializable

@Serializable
data class ScheduleModelResponse(
    val success: Boolean,
    val reason: String?=null,
    val schedules: List<ScheduleModel>
)
