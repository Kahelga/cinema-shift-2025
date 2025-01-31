package com.example.cinemashift2025.detailes.data.converter

import com.example.cinemashift2025.detailes.data.model.DetailsModel

import com.example.cinemashift2025.poster.data.model.FilmActorModel
import com.example.cinemashift2025.poster.data.model.FilmCountryModel
import com.example.cinemashift2025.poster.data.model.FilmDirectorModel

import com.example.cinemashift2025.poster.data.model.FilmUserRatingModel
import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.entity.FilmActor
import com.example.cinemashift2025.poster.domain.entity.FilmCountry
import com.example.cinemashift2025.poster.domain.entity.FilmDirector

import com.example.cinemashift2025.poster.domain.entity.FilmUserRating

class DetailConverter {
    private fun convertActor(actorModel: FilmActorModel): FilmActor {
        return FilmActor(
            id = actorModel.id,
            professions = actorModel.professions,
            fullName = actorModel.fullName
        )
    }

    private fun convertDirector(directorModel: FilmDirectorModel): FilmDirector {
        return FilmDirector(
            id = directorModel.id,
            professions = directorModel.professions,
            fullName = directorModel.fullName
        )
    }

    private fun convertUserRating(userRatingModel: FilmUserRatingModel): FilmUserRating {
        return FilmUserRating(
            kinopoisk = userRatingModel.kinopoisk,
            imdb = userRatingModel.imdb
        )
    }

    private fun convertCountry(countryModel: FilmCountryModel): FilmCountry {
        return FilmCountry(
            name = countryModel.name,
            code = countryModel.code,
            code2 = countryModel.code2,
            id = countryModel.id
        )
    }

    fun convert(model: DetailsModel): Film =
        Film(
            id = model.film.id,
            name = model.film.name,
            originalName = model.film.originalName,
            description = model.film.description,
            releaseDate = model.film.releaseDate,
            actors = model.film.actors.map { convertActor(it) },
            directors = model.film.directors.map { convertDirector(it) },
            runtime = model.film.runtime,
            ageRating = model.film.ageRating,
            genres = model.film.genres,
            userRatings = convertUserRating(model.film.userRatings),
            img = model.film.img,
            country = convertCountry(model.film.country)
        )
}