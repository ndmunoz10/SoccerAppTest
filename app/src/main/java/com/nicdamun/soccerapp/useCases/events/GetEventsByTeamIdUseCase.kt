package com.nicdamun.soccerapp.useCases.events

import com.nicdamun.core.models.EventModel
import com.nicdamun.repository.events.EventRepository

class GetEventsByTeamIdUseCase(
    private val eventRepository: EventRepository
) {

    suspend fun invoke(teamId: String): List<EventModel> {
        return eventRepository.getEventsByTeamId(teamId)
    }
}