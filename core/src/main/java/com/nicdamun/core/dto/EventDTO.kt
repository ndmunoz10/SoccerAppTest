package com.nicdamun.core.dto

import com.google.gson.annotations.SerializedName

data class EventDTO (
    @SerializedName("strEvent") val name: String?,
    @SerializedName("strLeague") val leagueName: String?,
    @SerializedName("dateEvent") val dateEvent: String?
)