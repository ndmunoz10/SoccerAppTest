package com.nicdamun.soccerapp.useCases.teams

import com.nicdamun.core.models.TeamModel
import com.nicdamun.repository.teams.TeamsRepository

class GetTeamsByLeagueNameUseCase(
    private val teamsRepository: TeamsRepository
) {

    suspend fun invoke(leagueName: String): List<TeamModel> {
        return teamsRepository.getTeamsByLeagueName(leagueName)
    }
}