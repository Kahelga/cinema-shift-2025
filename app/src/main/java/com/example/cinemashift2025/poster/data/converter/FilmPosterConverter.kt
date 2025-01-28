package com.example.cinemashift2025.poster.data.converter

import com.example.cinemashift2025.poster.data.model.FilmActorModel
import com.example.cinemashift2025.poster.data.model.FilmCountryModel
import com.example.cinemashift2025.poster.data.model.FilmDirectorModel
import com.example.cinemashift2025.poster.data.model.FilmModel
import com.example.cinemashift2025.poster.data.model.FilmUserRatingModel
import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.domain.entity.FilmActor
import com.example.cinemashift2025.poster.domain.entity.FilmCountry
import com.example.cinemashift2025.poster.domain.entity.FilmDirector
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


    fun convert(model:FilmModel): Film =
        Film(
            id =model.id,
            name = model.name,
            originalNam =model.originalNam,
            description =model.description,
            releaseDate =model.releaseDate,
            actors =model.actors.map { convertActor(it) },
            directors =model.directors.map { convertDirector(it) },
            runtime =model.runtime,
            ageRatings =model.ageRatings,
            genres =model.genres,
            userRatings =convertUserRating(model.userRatings),
            img =model.img,
            country =convertCountry(model.country)
        )

}