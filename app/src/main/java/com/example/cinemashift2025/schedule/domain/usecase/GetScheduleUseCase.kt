package com.example.cinemashift2025.schedule.domain.usecase

import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse
import com.example.cinemashift2025.schedule.domain.repository.ScheduleRepository

class GetScheduleUseCase (private val scheduleRepository: ScheduleRepository) {
    suspend operator fun invoke(filmId:String): ScheduleResponse =
        scheduleRepository.get(filmId)

}