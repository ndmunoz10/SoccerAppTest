package com.nicdamun.network.api

import com.nicdamun.core.dto.BaseEventResponse
import com.nicdamun.core.dto.BaseTeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsAPI {

    @GET("search_all_teams.php")
    suspend fun getTeamsByLeague(@Query("l") leagueName: String): Response<BaseTeamResponse>

    @GET("eventsnext.php")
    suspend fun getEventsByTeamId(@Query("id") teamId: String): Response<BaseEventResponse>
}