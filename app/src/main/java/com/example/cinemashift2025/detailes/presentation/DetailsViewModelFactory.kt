package com.example.cinemashift2025.detailes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinemashift2025.detailes.domain.usecase.GetFilmUseCase

class DetailsViewModelFactory(
    private val filmId: String,
    private val getFilmUseCase: GetFilmUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == DetailsViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return DetailsViewModel(filmId, getFilmUseCase) as T
    }
}