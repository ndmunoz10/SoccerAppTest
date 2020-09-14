package com.nicdamun.core.mappers

import com.nicdamun.core.dto.EventDTO
import com.nicdamun.core.models.EventModel

fun EventDTO.toEventModel(): EventModel {
    return EventModel(
        name = name.orEmpty(),
        leagueName = leagueName.orEmpty(),
        dateEvent = dateEvent.orEmpty()
    )
}