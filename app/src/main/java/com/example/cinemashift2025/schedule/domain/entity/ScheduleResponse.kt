package com.example.cinemashift2025.schedule.domain.entity

data class ScheduleResponse(
    val success: Boolean,
    val reason: String?=null,
    val schedules: List<Schedule>
)
