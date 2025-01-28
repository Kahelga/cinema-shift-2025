package com.example.cinemashift2025.poster.data.network

import com.example.cinemashift2025.poster.data.model.FilmModel
import retrofit2.http.GET

interface FilmPosterApi {
    @GET("today")
    suspend fun getAll(): List<FilmModel>
}