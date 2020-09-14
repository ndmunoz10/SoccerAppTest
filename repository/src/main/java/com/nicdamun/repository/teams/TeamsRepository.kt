package com.nicdamun.repository.teams

import com.nicdamun.core.mappers.toTeamModel
import com.nicdamun.core.models.TeamModel
import com.nicdamun.repository.helpers.Result

class TeamsRepository(
    private val teamsRemoteDataSource: TeamsRemoteDataSource
) {

    suspend fun getTeamsByLeagueName(leagueName: String): List<TeamModel> {
        return when(val result = teamsRemoteDataSource.getTeamsByLeagueName(leagueName)) {
            is Result.Success -> result.data.mapNotNull { teamDTO -> teamDTO.toTeamModel() }
            is Result.Failure -> emptyList()
        }
    }
}