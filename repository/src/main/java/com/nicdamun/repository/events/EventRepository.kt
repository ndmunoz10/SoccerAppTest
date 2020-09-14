package com.nicdamun.repository.events

import com.nicdamun.core.mappers.toEventModel
import com.nicdamun.core.models.EventModel
import com.nicdamun.repository.helpers.Result

class EventRepository(
    private val eventRemoteDataSource: EventRemoteDataSource
) {

    suspend fun getEventsByTeamId(teamId: String): List<EventModel> {
        return when(val result = eventRemoteDataSource.getEventsByTeamId(teamId)) {
            is Result.Success -> result.data.map { eventDTO -> eventDTO.toEventModel() }
            is Result.Failure -> emptyList()
        }
    }
}