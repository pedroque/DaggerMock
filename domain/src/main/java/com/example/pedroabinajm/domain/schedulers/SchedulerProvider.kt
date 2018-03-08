package com.example.pedroabinajm.domain.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun computation() : Scheduler
    fun ui() : Scheduler
}