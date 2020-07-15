package com.myapps.weather.di

import com.myapps.weather.data.RemoteRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun getRemoteRepository(): RemoteRepository
}