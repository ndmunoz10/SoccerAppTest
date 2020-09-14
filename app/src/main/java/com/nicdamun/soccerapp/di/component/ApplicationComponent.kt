package com.nicdamun.soccerapp.di.component

import android.content.Context
import com.nicdamun.network.di.module.NetworkModule
import com.nicdamun.repository.di.module.DataSourceModule
import com.nicdamun.repository.di.module.RepositoryModule
import com.nicdamun.soccerapp.common.BaseApplication
import com.nicdamun.soccerapp.di.module.AndroidComponentsModule
import com.nicdamun.soccerapp.di.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidComponentsModule::class,
        AndroidSupportInjectionModule::class,
        DataSourceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        fun createContext(@BindsInstance context: Context): Builder
        fun createDataSourceModule(dataSourceModule: DataSourceModule): Builder
        fun createNetworkModule(networkModule: NetworkModule): Builder
        fun createRepositoryModule(repositoryModule: RepositoryModule): Builder
        fun createUseCaseModule(useCaseModule: UseCaseModule): Builder
        fun build(): ApplicationComponent
    }
}