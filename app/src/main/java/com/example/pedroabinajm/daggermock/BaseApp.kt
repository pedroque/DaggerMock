package com.example.pedroabinajm.daggermock

import android.app.Application
import dagger.android.HasActivityInjector
import android.app.Activity
import com.example.pedroabinajm.daggermock.di.AppModule
import com.example.pedroabinajm.daggermock.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class BaseApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}