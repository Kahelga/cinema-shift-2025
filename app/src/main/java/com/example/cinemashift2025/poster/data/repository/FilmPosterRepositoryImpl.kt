package com.example.cinemashift2025.poster.data.repository

import com.example.cinemashift2025.poster.data.converter.FilmPosterConverter
import com.example.cinemashift2025.poster.data.network.FilmPosterApi
import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.repository.FilmPosterRepository

class FilmPosterRepositoryImpl (private val filmPosterApi:FilmPosterApi,private val filmPosterConverter: FilmPosterConverter):FilmPosterRepository{
    override suspend fun getAll(): List<Film> {
        val models = filmPosterApi.getAll()
        return models.map {filmPosterConverter.convert(it)}
    }

}