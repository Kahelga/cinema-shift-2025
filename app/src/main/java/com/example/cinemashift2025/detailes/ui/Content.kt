package com.example.cinemashift2025.detailes.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier


import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp

import com.example.cinemashift2025.R
import com.example.cinemashift2025.poster.domain.entity.Film
import com.example.cinemashift2025.poster.ui.FilmImage
import com.example.cinemashift2025.poster.ui.FilmRating
import com.example.cinemashift2025.poster.ui.FilmRuntime
import com.example.cinemashift2025.poster.ui.FilmTitle


import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import com.example.cinemashift2025.schedule.domain.entity.ScheduleSeances

@Composable
fun Content(
    film: Film,
    scheduleResponse: ScheduleResponse
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        item {
            DetailItem(film, scheduleResponse)
        }
    }
}


@Composable
private fun DetailItem(
    film: Film,
    scheduleResponse: ScheduleResponse
) {
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        FilmImage(film)
        FilmTitle(film)
        FilmRuntime(film)
        FilmRating(film)
        FilmActors(film)
        FilmDescription(film, isDescriptionExpanded) { isDescriptionExpanded = it }
        ScheduleDisplay(scheduleResponse)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilmActors(film: Film) {
    Text(
        text = stringResource(R.string.details_actors),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(2.dp)
    )
    FlowRow(
        modifier = Modifier.padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        film.actors.forEachIndexed { index, actor ->
            Text(text = actor.fullName)
            if (index < film.actors.size - 1) {
                Text(text = ", ")
            }
        }
    }
}

@Composable
private fun FilmDescription(
    film: Film,
    isDescriptionExpanded: Boolean,
    onDescriptionToggle: (Boolean) -> Unit
) {
    Column(modifier = Modifier.padding(2.dp)) {
        Text(
            text = stringResource(R.string.details_description),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        if (isDescriptionExpanded) {
            Text(text = film.description)
        } else {
            Text(
                text = film.description.take(100) + "...",
                modifier = Modifier.clickable { onDescriptionToggle(true) },
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = if (isDescriptionExpanded)
                stringResource(R.string.details_description_less)
            else stringResource(
                R.string.details_description_more
            ),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { onDescriptionToggle(!isDescriptionExpanded) }
        )
    }
}

/*@Composable
fun ScheduleDisplay(scheduleResponse: ScheduleResponse) {
    Text(
        text = stringResource(R.string.details_schedule),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(2.dp)
    )

    scheduleResponse.schedules.forEach { schedule ->

        val localDate = parseDate(schedule.date)

        localDate?.let {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Расписание на: ${it}",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Отображение сеансов
                schedule.seances.forEach { seance ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${seance.time} - ${seance.hall.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}*/


@Composable
private fun ScheduleDisplay(scheduleResponse: ScheduleResponse) {
    // var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedDate by remember {
        mutableStateOf<LocalDate?>(
            scheduleResponse.schedules.firstOrNull()?.let { parseDate(it.date) })
    }
    var selectedSeance by remember { mutableStateOf<ScheduleSeances?>(null) }

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM")

    Text(
        text = stringResource(R.string.details_schedule),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(2.dp)
    )

    // Отображение дат в виде кнопок
    LazyRow(modifier = Modifier.padding(8.dp)) {
        items(scheduleResponse.schedules) { schedule ->
            val localDate = parseDate(schedule.date)

            localDate?.let {
                Button(
                    onClick = {
                        selectedDate = it
                        selectedSeance = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedDate == it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .padding(4.dp)
                        .height(50.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    Text(
                        text = it.format(dateFormatter),
                        color = if (selectedDate == it) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
        }
    }

    selectedDate?.let { date ->
        val groupedSeances = scheduleResponse.schedules
            .find { parseDate(it.date) == date }
            ?.seances
            ?.groupBy { it.hall.name }

        groupedSeances?.forEach { (hallName, seances) ->
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = hallName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                ) {
                    seances.forEach { seance ->
                        Button(
                            onClick = {
                                selectedSeance = seance
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedSeance == seance) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier
                                .padding(4.dp)
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.inverseSurface,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            Text(
                                text = seance.time,
                                color = if (selectedSeance == seance) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.inverseSurface
                            )
                        }
                    }
                }
            }
        }
    }

}

private fun parseDate(dateString: String): LocalDate? {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    return try {
        LocalDate.parse(dateString, formatter)
    } catch (e: DateTimeParseException) {
        null
    }
}
