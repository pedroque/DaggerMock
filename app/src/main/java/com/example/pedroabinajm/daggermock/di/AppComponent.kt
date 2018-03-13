package com.example.pedroabinajm.daggermock.di

import android.app.Application
import com.example.pedroabinajm.daggermock.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent : AndroidInjector<BaseApp>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApp>() {
        @BindsInstance
        abstract fun application(application: Application): Builder
        abstract fun appModule(appModule: AppModule): Builder
    }
}