package com.example.cinemashift2025.detailes.domain.entity

import com.example.cinemashift2025.poster.domain.entity.Film

data class Detail(
    val success: Boolean,
    // val reason: String,
    val film: Film
)
