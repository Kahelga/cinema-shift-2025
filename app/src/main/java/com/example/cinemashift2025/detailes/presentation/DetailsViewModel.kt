package com.example.cinemashift2025.detailes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemashift2025.detailes.domain.usecase.GetFilmUseCase
import com.example.cinemashift2025.schedule.domain.usecase.GetScheduleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class DetailsViewModel(
    private val filmId: String,
    private val getFilmUseCase: GetFilmUseCase,
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(DetailsState.Initial)
    val state: StateFlow<DetailsState> = _state

    fun loadFilm() {
        viewModelScope.launch {
            _state.value = DetailsState.Loading

            try {
                val film = getFilmUseCase(filmId)
                val schedule=getScheduleUseCase(filmId)
                _state.value = DetailsState.Content(film,schedule)
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = DetailsState.Failure(ex.message)
            }
        }
    }

}