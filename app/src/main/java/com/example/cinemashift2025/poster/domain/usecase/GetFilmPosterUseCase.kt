package com.example.cinemashift2025.poster.domain.usecase

import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.entity.FilmResponse
import com.example.cinemashift2025.poster.domain.repository.FilmPosterRepository

class GetFilmPosterUseCase(private val repository: FilmPosterRepository) {
    suspend operator fun invoke(): FilmResponse = repository.getAll()

}