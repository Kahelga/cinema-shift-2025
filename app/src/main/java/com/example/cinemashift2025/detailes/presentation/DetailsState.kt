package com.example.cinemashift2025.detailes.presentation

import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse

interface DetailsState {
    data object Initial : DetailsState
    data object Loading : DetailsState
    data class Failure(val message: String?) : DetailsState
    data class Content(val film: Film,val scheduleResponse: ScheduleResponse) : DetailsState
}