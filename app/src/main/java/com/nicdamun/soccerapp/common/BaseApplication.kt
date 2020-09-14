package com.nicdamun.soccerapp.common

import com.nicdamun.network.di.module.NetworkModule
import com.nicdamun.repository.di.module.DataSourceModule
import com.nicdamun.repository.di.module.RepositoryModule
import com.nicdamun.soccerapp.di.component.DaggerApplicationComponent
import com.nicdamun.soccerapp.di.module.UseCaseModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .createContext(applicationContext)
            .createNetworkModule(NetworkModule())
            .createDataSourceModule(DataSourceModule())
            .createRepositoryModule(RepositoryModule())
            .createUseCaseModule(UseCaseModule())
            .build()
    }

}