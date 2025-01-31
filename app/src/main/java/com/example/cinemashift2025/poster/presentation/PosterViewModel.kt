package com.example.cinemashift2025.poster.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemashift2025.poster.domain.usecase.GetFilmPosterUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PosterViewModel(private val getFilmPosterUseCase: GetFilmPosterUseCase) : ViewModel() {
    private val _state = MutableStateFlow<PosterState>(PosterState.Initial)
    val state: StateFlow<PosterState> = _state

    fun loadFilms() {
        viewModelScope.launch {
            _state.value = PosterState.Loading
            try {
                val films = getFilmPosterUseCase()
                _state.value = PosterState.Content(films)

            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = PosterState.Failure(ex.localizedMessage.orEmpty())
            }
        }
    }


}