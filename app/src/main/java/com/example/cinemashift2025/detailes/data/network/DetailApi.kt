package com.example.cinemashift2025.detailes.data.network

import com.example.cinemashift2025.detailes.data.model.DetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("film/{filmId}")
    suspend fun get(@Path("filmId") filmId: String): DetailsModel
}