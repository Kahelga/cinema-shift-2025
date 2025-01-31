package com.example.cinemashift2025.poster.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cinemashift2025.poster.domain.entity.Film
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

import coil.request.ImageRequest

import com.example.cinemashift2025.R
import com.example.cinemashift2025.poster.domain.entity.FilmResponse

@Composable
fun Content(
    films: FilmResponse,
    onItemClicked: (filmId: String) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(films.films) { film ->
            FilmItem(
                film,
                onItemClicked = { onItemClicked(film.id) }
            )
        }
    }
}


@Composable
private fun FilmItem(
    item: Film,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 6.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            FilmImage(item)
            FilmTitle(item)
            FilmRuntime(item)
            FilmRating(item)
            FilmButton(onItemClicked)
        }
    }
}

@Composable
fun FilmImage(item: Film) {
    val baseUrl = "https://shift-intensive.ru/api"
    val imagePath = item.img
    val fullUrl = "$baseUrl$imagePath"

    Box(
        modifier = Modifier
            .height(600.dp)
            .fillMaxWidth()
            .padding(top = 8.dp, start = 5.dp, end = 5.dp)
    ) {
        // загрузка изображения
        val context = LocalContext.current
        val imageRequest = ImageRequest.Builder(context)
            .data(fullUrl)
            .crossfade(true)
            .build()
        val painter = rememberAsyncImagePainter(imageRequest)

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop
        )

        // отображение жанров, страны и года
        val genres = item.genres.joinToString(", ")
        val year = getYearFromReleaseDate(item.releaseDate)

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(2.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(2.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(genres)
                    }
                    append("\n")
                    append("${item.country.name}, $year")
                },
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
            )
        }
    }
}

@Composable
fun FilmTitle(item: Film) {
    Text(
        text = buildAnnotatedString {
            append(item.name)
            append(" (${item.ageRating})")
        },
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)
    )
}

@Composable
fun FilmRuntime(item: Film) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = stringResource(R.string.runtime_film, item.runtime),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun FilmRating(item: Film) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Box(
            modifier = Modifier.size(30.dp)
        ) {
            // контур иконки
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = stringResource(R.string.rating_platform, item.userRatings.kinopoisk),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
private fun FilmButton(onItemClicked: () -> Unit) {
    Button(
        onClick = onItemClicked,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 20.dp, bottom = 8.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.button_details),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}


private fun getYearFromReleaseDate(releaseDate: String): String {
    return releaseDate.split(" ").last()
}