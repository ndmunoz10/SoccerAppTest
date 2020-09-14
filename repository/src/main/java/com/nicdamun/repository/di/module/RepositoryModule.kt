package com.nicdamun.repository.di.module

import com.nicdamun.repository.events.EventRemoteDataSource
import com.nicdamun.repository.events.EventRepository
import com.nicdamun.repository.teams.TeamsRemoteDataSource
import com.nicdamun.repository.teams.TeamsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideEventRepository(
        eventRemoteDataSource: EventRemoteDataSource
    ): EventRepository =
        EventRepository(eventRemoteDataSource)

    @Provides
    fun provideTeamRepository(
        teamsRemoteDataSource: TeamsRemoteDataSource
    ): TeamsRepository =
        TeamsRepository(teamsRemoteDataSource)
}