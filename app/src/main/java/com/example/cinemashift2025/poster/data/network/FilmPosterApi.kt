package com.example.cinemashift2025.poster.data.network

import com.example.cinemashift2025.poster.data.model.FilmResponseModel
import retrofit2.http.GET

interface FilmPosterApi {
    @GET("today")
    suspend fun getAll(): FilmResponseModel
}