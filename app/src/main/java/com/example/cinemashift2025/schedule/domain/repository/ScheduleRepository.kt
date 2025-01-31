package com.example.cinemashift2025.schedule.domain.repository

import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse

interface ScheduleRepository {
    suspend fun get(filmId: String): ScheduleResponse
}