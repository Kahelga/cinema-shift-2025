package com.example.cinemashift2025.schedule.domain.entity

data class Schedule(
    val date:String,
    val seances:List<ScheduleSeances>,
)
