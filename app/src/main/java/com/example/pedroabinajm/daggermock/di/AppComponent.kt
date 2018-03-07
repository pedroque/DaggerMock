package com.example.pedroabinajm.daggermock.di

import com.example.pedroabinajm.daggermock.BaseApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent : AndroidInjector<BaseApp>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApp>(){
        abstract fun appModule(appModule: AppModule): Builder
    }
}