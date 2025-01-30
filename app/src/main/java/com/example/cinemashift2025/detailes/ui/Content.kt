package com.example.cinemashift2025.detailes.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.cinemashift2025.poster.domain.entity.Film


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Content(
    film: Film,
) {
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    val genres = film.genres.joinToString(", ")
    val year = getYearFromReleaseDate(film.releaseDate)
    val baseUrl = "https://shift-intensive.ru/api"
    val imagePath = film.img
    val fullUrl = "$baseUrl$imagePath"

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // Изображение фильма
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 5.dp, end = 5.dp)
            ) {
                // загрузка изображения
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = fullUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )

                // Отображаем загруженное изображение
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )

                // отображение жанров, страны и года
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
                            append("${film.country.name}, $year")
                        },
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
                    )
                }
            }
        }

        // Заголовок фильма
        item {
            Text(
                text = buildAnnotatedString {
                    append(film.name) // Имя фильма
                    append(" (${film.ageRating})") // Возраст в скобках
                },
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)
            )
        }

        // Отображение времени
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp) // Отступы
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = stringResource(R.string.runtime_film, film.runtime),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        // Отображение рейтинга
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
            ) {
                Box(modifier = Modifier.size(30.dp)) {
                    // Контур иконки
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Рейтинг контуром",
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Рейтинг",
                        tint = Color.Yellow,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.rating_platform, film.userRatings.kinopoisk),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        // Актеры
        item {
            Text(
                text = stringResource(R.string.details_actors),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(2.dp)
            )
            FlowRow(
                modifier = Modifier.padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                overflow = FlowRowOverflow.Clip
            ) {
                film.actors.forEachIndexed { index, actor ->
                    Text(
                        text = actor.fullName,
                       // modifier = Modifier.padding(end = 1.dp)
                    )
                    if (index < film.actors.size - 1) {
                        Text(text = ", ")
                    }
                }
            }
        }

        // Описание фильма
        item {
            Column(modifier = Modifier.padding(2.dp)) {
                Text(
                    text = stringResource(R.string.details_description),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                // Текст описания
                if (isDescriptionExpanded) {
                    Text(text = film.description)
                } else {
                    Text(
                        text = film.description.take(100) + "...", // Показать первые 100 символов
                        modifier = Modifier.clickable { isDescriptionExpanded = true },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                // Для раскрытия/скрытия описания
                Text(
                    text = if (isDescriptionExpanded) stringResource(R.string.details_description_less) else stringResource(R.string.details_description_more),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clickable { isDescriptionExpanded = !isDescriptionExpanded }
                )
            }
        }
    }
}

private fun getYearFromReleaseDate(releaseDate: String): String {
    return releaseDate.split(" ").last()
}