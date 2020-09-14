package com.nicdamun.core.mappers

import com.nicdamun.core.dto.TeamDTO
import com.nicdamun.core.models.TeamModel

fun TeamDTO.toTeamModel(): TeamModel? {
    return if (id == null) {
        null
    }
    else {
        TeamModel(
            id = id,
            name = team.orEmpty(),
            stadiumName = stadium.orEmpty(),
            badge = badge.orEmpty(),
            banner = banner.orEmpty(),
            description = description.orEmpty(),
            formedYear = formedYear ?: 0,
            jersey = jersey.orEmpty(),
            website = website.orEmpty(),
            instagram = instagram.orEmpty(),
            facebook = facebook.orEmpty(),
            twitter = twitter.orEmpty()
        )
    }
}