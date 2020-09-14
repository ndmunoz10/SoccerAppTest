package com.nicdamun.core.dto

import com.google.gson.annotations.SerializedName

data class TeamDTO (
    @SerializedName("idTeam") val id: String?,
    @SerializedName("strTeam") val team: String?,
    @SerializedName("strStadium") val stadium: String?,
    @SerializedName("strTeamBadge") val badge: String?,
    @SerializedName("strTeamBanner") val banner: String?,
    @SerializedName("strDescriptionEN") val description: String?,
    @SerializedName("intFormedYear") val formedYear: Int?,
    @SerializedName("strTeamJersey") val jersey: String?,
    @SerializedName("strWebsite") val website: String?,
    @SerializedName("strFacebook") val facebook: String?,
    @SerializedName("strTwitter") val twitter: String?,
    @SerializedName("strInstagram") val instagram: String?
)