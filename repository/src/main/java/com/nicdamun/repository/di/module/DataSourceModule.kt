package com.nicdamun.repository.di.module

import com.nicdamun.network.api.SportsAPI
import com.nicdamun.repository.events.EventRemoteDataSource
import com.nicdamun.repository.teams.TeamsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideEventRemoteDataSource(
        sportsAPI: SportsAPI
    ): EventRemoteDataSource =
        EventRemoteDataSource(sportsAPI)

    @Provides
    fun provideTeamsRemoteDataSource(
        sportsAPI: SportsAPI
    ): TeamsRemoteDataSource =
        TeamsRemoteDataSource(sportsAPI)
}