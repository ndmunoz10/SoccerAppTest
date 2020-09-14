package com.nicdamun.repository.teams

import com.nicdamun.network.api.SportsAPI
import com.nicdamun.core.dto.TeamDTO
import com.nicdamun.repository.extensions.callServices
import com.nicdamun.repository.extensions.safeApiCall
import com.nicdamun.repository.helpers.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamsRemoteDataSource(
    private val sportsAPI: SportsAPI
) {

    suspend fun getTeamsByLeagueName(leagueName: String): Result<List<TeamDTO>> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    sportsAPI.getTeamsByLeague(leagueName).callServices()
                }
            )
            when(result) {
                is Result.Success -> {
                    val teams = result.data.teams
                    if (teams == null) Result.Failure(Exception("teams property in json does not exist"))
                    else Result.Success(teams)
                }
                is Result.Failure -> result
            }
        }
    }
}