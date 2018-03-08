package com.example.pedroabinajm.daggermock.schedulers

import com.example.pedroabinajm.domain.schedulers.SchedulerProvider
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AndroidTestScheduleProvider @Inject constructor() : SchedulerProvider {

    override fun computation() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()
}