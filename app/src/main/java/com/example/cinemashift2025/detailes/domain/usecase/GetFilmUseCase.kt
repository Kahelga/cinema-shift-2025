package com.example.cinemashift2025.detailes.domain.usecase

import com.example.cinemashift2025.detailes.domain.repository.DetailRepository
import com.example.cinemashift2025.poster.domain.entity.Film

class GetFilmUseCase(private val detailRepository: DetailRepository) {
    suspend operator fun invoke(filmId:String): Film =
        detailRepository.get(filmId)

}