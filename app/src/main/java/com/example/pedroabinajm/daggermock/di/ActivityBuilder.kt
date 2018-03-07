package com.example.pedroabinajm.daggermock.di

import com.example.pedroabinajm.daggermock.view.hamburgers.HamburgersActivity
import dagger.Module
import com.example.pedroabinajm.daggermock.view.hamburgers.HamburgersModule
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [HamburgersModule::class])
    abstract fun bindHamburgersActivity() : HamburgersActivity
}