package com.nicdamun.soccerapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicdamun.soccerapp.di.viewModelFactory.DaggerViewModelFactory
import com.nicdamun.soccerapp.di.viewModelFactory.ViewModelKey
import com.nicdamun.soccerapp.teamDetail.TeamDetailViewModel
import com.nicdamun.soccerapp.teamList.TeamListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TeamDetailViewModel::class)
    abstract fun bindTeamDetailViewModel(teamDetailViewModel: TeamDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamListViewModel::class)
    abstract fun bindTeamListViewModel(teamListViewModel: TeamListViewModel): ViewModel
}