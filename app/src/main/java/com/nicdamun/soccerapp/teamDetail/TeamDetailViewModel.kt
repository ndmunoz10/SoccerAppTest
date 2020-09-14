package com.nicdamun.soccerapp.teamDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicdamun.core.models.EventModel
import com.nicdamun.soccerapp.useCases.events.GetEventsByTeamIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamDetailViewModel @Inject constructor(
    private val getEventsByTeamIdUseCase: GetEventsByTeamIdUseCase
): ViewModel() {

    private val teamEventsObs = MutableLiveData<List<EventModel>>()

    fun teamEventsObs(): LiveData<List<EventModel>> = teamEventsObs

    fun fetchTeamEventsById(teamId: String) {
        viewModelScope.launch {
            teamEventsObs.value = getEventsByTeamIdUseCase.invoke(teamId)
        }
    }
}