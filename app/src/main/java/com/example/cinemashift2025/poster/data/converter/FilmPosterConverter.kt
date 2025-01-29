package com.example.cinemashift2025.poster.data.converter

import com.example.cinemashift2025.poster.data.model.FilmActorModel
import com.example.cinemashift2025.poster.data.model.FilmCountryModel
import com.example.cinemashift2025.poster.data.model.FilmDirectorModel
import com.example.cinemashift2025.poster.data.model.FilmModel
import com.example.cinemashift2025.poster.data.model.FilmResponseModel
import com.example.cinemashift2025.poster.data.model.FilmUserRatingModel
import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.entity.FilmActor
import com.example.cinemashift2025.poster.domain.entity.FilmCountry
import com.example.cinemashift2025.poster.domain.entity.FilmDirector
import com.example.cinemashift2025.poster.domain.entity.FilmResponse
import com.example.cinemashift2025.poster.domain.entity.FilmUserRating

class FilmPosterConverter {
    fun convertActor(actorModel: FilmActorModel): FilmActor {
        return FilmActor(
            id = actorModel.id,
            professions = actorModel.professions,
            fullName = actorModel.fullName
        )
    }

    fun convertDirector(directModel: FilmDirectorModel): FilmDirector {
        return FilmDirector(
            id = directModel.id,
            professions = directModel.professions,
            fullName = directModel.fullName
        )
    }
    fun convertUserRating(userRatingModel: FilmUserRatingModel):FilmUserRating{
        return FilmUserRating(
            kinopoisk = userRatingModel.kinopoisk,
            imdb = userRatingModel.imdb
        )
    }

    fun convertCountry(countryModel: FilmCountryModel):FilmCountry{
        return FilmCountry(
            name = countryModel.name,
            code = countryModel.code,
            code2 = countryModel.code2,
            id=countryModel.id
        )
    }
fun convertFilm(filmModel: FilmModel):Film{
    return Film(
        id =filmModel.id,
        name = filmModel.name,
        originalName=filmModel.originalName,
        description =filmModel.description,
        releaseDate =filmModel.releaseDate,
        actors =filmModel.actors.map { convertActor(it) },
        directors =filmModel.directors.map { convertDirector(it) },
        runtime =filmModel.runtime,
        ageRating =filmModel.ageRating,
        genres =filmModel.genres,
        userRatings =convertUserRating(filmModel.userRatings),
        img =filmModel.img,
        country =convertCountry(filmModel.country)
    )
}

    fun convert(model: FilmResponseModel): FilmResponse =
        FilmResponse(
            success = model.success,
           // reason = model.reason,
            films = model.films.map { convertFilm(it) }
        )

}