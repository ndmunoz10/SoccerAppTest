package com.nicdamun.network.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.nicdamun.network.BuildConfig
import com.nicdamun.network.api.SportsAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideSportsAPI(): SportsAPI =
        provideRetrofit(BuildConfig.BASE_URL)
            .create(SportsAPI::class.java)

    @Suppress("SameParameterValue")
    private fun provideRetrofit(baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.callTimeout(30, TimeUnit.SECONDS)
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}