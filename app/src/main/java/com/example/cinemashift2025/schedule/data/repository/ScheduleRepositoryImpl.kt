package com.example.cinemashift2025.schedule.data.repository

import com.example.cinemashift2025.schedule.data.converter.ScheduleConverter
import com.example.cinemashift2025.schedule.data.network.ScheduleApi
import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse
import com.example.cinemashift2025.schedule.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(
    private val scheduleApi: ScheduleApi,
    private val scheduleConverter: ScheduleConverter
) : ScheduleRepository {
    override suspend fun get(filmId: String): ScheduleResponse{
        val model = scheduleApi.get(filmId)
        return scheduleConverter.convert(model)
    }

}