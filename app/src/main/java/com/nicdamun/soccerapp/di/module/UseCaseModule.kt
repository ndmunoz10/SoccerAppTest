package com.nicdamun.soccerapp.di.module

import com.nicdamun.repository.events.EventRepository
import com.nicdamun.repository.teams.TeamsRepository
import com.nicdamun.soccerapp.useCases.events.GetEventsByTeamIdUseCase
import com.nicdamun.soccerapp.useCases.teams.GetTeamsByLeagueNameUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetEventsByTeamIdUseCase(
        eventRepository: EventRepository
    ): GetEventsByTeamIdUseCase =
        GetEventsByTeamIdUseCase(eventRepository)

    @Provides
    fun provideGetTeamsByLeagueNameUseCase(
        teamsRepository: TeamsRepository
    ): GetTeamsByLeagueNameUseCase =
        GetTeamsByLeagueNameUseCase(teamsRepository)
}