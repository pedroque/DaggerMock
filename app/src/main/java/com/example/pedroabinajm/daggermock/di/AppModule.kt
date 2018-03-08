package com.example.pedroabinajm.daggermock.di

import android.app.Application
import com.example.pedroabinajm.daggermock.BaseApp
import com.example.pedroabinajm.daggermock.schedulers.SchedulerProviderImpl
import com.example.pedroabinajm.domain.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(
        private val application: Application
) {

    @Provides
    @Singleton
    internal fun provideBaseApp() = application as BaseApp

    @Provides
    @Singleton
    internal fun provideApplicationContext() = application.applicationContext

    @Provides
    internal fun provideSchedulerProvider(schedulerProvider: SchedulerProviderImpl):
            SchedulerProvider = schedulerProvider
}