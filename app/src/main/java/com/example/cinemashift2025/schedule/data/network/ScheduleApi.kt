package com.example.cinemashift2025.schedule.data.network

import com.example.cinemashift2025.detailes.data.model.DetailsModel
import com.example.cinemashift2025.schedule.data.model.ScheduleModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {
    @GET("film/{filmId}/schedule")
    suspend fun get(@Path("filmId") filmId: String): ScheduleModelResponse
}