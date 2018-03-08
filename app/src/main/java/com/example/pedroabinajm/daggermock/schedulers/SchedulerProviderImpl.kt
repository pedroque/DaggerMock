package com.example.pedroabinajm.daggermock.schedulers

import com.example.pedroabinajm.domain.schedulers.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl @Inject constructor() : SchedulerProvider {
    override fun computation() = Schedulers.computation()

    override fun ui() = AndroidSchedulers.mainThread()!!
}