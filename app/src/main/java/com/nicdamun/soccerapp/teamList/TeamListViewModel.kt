package com.nicdamun.soccerapp.teamList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicdamun.core.models.TeamModel
import com.nicdamun.soccerapp.useCases.teams.GetTeamsByLeagueNameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamListViewModel @Inject constructor(
    private val getTeamsByLeagueNameUseCase: GetTeamsByLeagueNameUseCase
): ViewModel() {

    private val teamsObs = MutableLiveData<List<TeamModel>>()

    fun getTeamObs(): LiveData<List<TeamModel>> = teamsObs

    fun getTeamsByLeagueName(leagueName: String) {
        viewModelScope.launch {
            teamsObs.value = getTeamsByLeagueNameUseCase.invoke(leagueName)
        }
    }
}