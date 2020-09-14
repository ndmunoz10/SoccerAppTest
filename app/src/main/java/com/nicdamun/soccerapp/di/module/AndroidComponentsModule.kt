package com.nicdamun.soccerapp.di.module

import com.nicdamun.soccerapp.teamDetail.TeamDetailActivity
import com.nicdamun.soccerapp.teamList.TeamListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidComponentsModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeTeamDetailActivity(): TeamDetailActivity

    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeTeamListActivity(): TeamListActivity
}