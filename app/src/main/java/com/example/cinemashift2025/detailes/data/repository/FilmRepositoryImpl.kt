package com.example.cinemashift2025.detailes.data.repository

import com.example.cinemashift2025.detailes.data.converter.DetailConverter
import com.example.cinemashift2025.detailes.data.network.DetailApi
import com.example.cinemashift2025.detailes.domain.repository.DetailRepository
import com.example.cinemashift2025.poster.domain.entity.Film

class FilmRepositoryImpl(
    private val detailApi: DetailApi,
    private val detailConverter: DetailConverter
):DetailRepository {
    override suspend fun get(filmId: String): Film {
        val model = detailApi.get(filmId)
        return  detailConverter.convert(model)
    }

}