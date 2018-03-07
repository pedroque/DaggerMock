package com.example.pedroabinajm.daggermock.di

import android.app.Application
import com.example.pedroabinajm.daggermock.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideBaseApp() = application as BaseApp

    @Provides
    @Singleton
    internal fun provideApplicationContext() = application.applicationContext

}