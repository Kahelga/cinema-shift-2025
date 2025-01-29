package com.example.cinemashift2025.poster.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinemashift2025.poster.domain.usecase.GetFilmPosterUseCase

class PosterViewModelFactory(private val getFilmPosterUseCase: GetFilmPosterUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PosterViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return PosterViewModel(getFilmPosterUseCase) as T
    }
}