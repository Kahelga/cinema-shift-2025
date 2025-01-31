package com.example.cinemashift2025.schedule.data.converter

import com.example.cinemashift2025.schedule.data.model.HallPlacesModel
import com.example.cinemashift2025.schedule.data.model.SeancesHallModel
import com.example.cinemashift2025.schedule.data.model.ScheduleModel
import com.example.cinemashift2025.schedule.data.model.ScheduleModelResponse
import com.example.cinemashift2025.schedule.data.model.ScheduleSeancesModel
import com.example.cinemashift2025.schedule.domain.entity.HallPlaces
import com.example.cinemashift2025.schedule.domain.entity.SeancesHall
import com.example.cinemashift2025.schedule.domain.entity.Schedule
import com.example.cinemashift2025.schedule.domain.entity.ScheduleResponse
import com.example.cinemashift2025.schedule.domain.entity.ScheduleSeances

class ScheduleConverter {
    private fun convertHallPlaces(hallPlacesModel: HallPlacesModel): HallPlaces {
        return HallPlaces(
            price = hallPlacesModel.price,
            type = hallPlacesModel.type
        )
    }

    private fun convertSeancesHall(seancesHallModel: SeancesHallModel): SeancesHall {
        return SeancesHall(
            name = seancesHallModel.name,
            places = seancesHallModel.places.map { row ->
                row.map { hallPlacesModel ->
                    convertHallPlaces(hallPlacesModel)
                }
            }
        )

    }

    private fun convertSheduleSeances(scheduleSeancesModel: ScheduleSeancesModel): ScheduleSeances {
        return ScheduleSeances(
            time = scheduleSeancesModel.time,
            hall = convertSeancesHall(scheduleSeancesModel.hall)
        )
    }

    private fun convertShedule(scheduleModel: ScheduleModel): Schedule {
        return Schedule(
            date = scheduleModel.date,
            seances = scheduleModel.seances.map { convertSheduleSeances(it) }
        )
    }

    fun convert(scheduleModelResponse: ScheduleModelResponse): ScheduleResponse {
        return ScheduleResponse(
            success = scheduleModelResponse.success,
            reason = scheduleModelResponse.reason,
            schedules = scheduleModelResponse.schedules.map { convertShedule(it) }
        )
    }

}