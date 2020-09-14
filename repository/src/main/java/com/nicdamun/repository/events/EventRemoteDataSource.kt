package com.nicdamun.repository.events

import com.nicdamun.core.dto.EventDTO
import com.nicdamun.network.api.SportsAPI
import com.nicdamun.repository.extensions.callServices
import com.nicdamun.repository.extensions.safeApiCall
import com.nicdamun.repository.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRemoteDataSource(
    private val sportsAPI: SportsAPI
) {

    suspend fun getEventsByTeamId(teamId: String): Result<List<EventDTO>> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    sportsAPI.getEventsByTeamId(teamId).callServices()
                }
            )
            when(result) {
                is Result.Success -> {
                    val events = result.data.events
                    if (events == null) Result.Failure(Exception("events property in json does not exist"))
                    else Result.Success(events)
                }
                is Result.Failure -> result
            }
        }
    }

}