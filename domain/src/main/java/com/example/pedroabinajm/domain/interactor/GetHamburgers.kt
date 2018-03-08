package com.example.pedroabinajm.domain.interactor

import com.example.pedroabinajm.domain.Hamburger
import com.example.pedroabinajm.domain.repository.HamburgerRepository
import com.example.pedroabinajm.domain.schedulers.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class GetHamburgers @Inject constructor(
        private val hamburguerRepository: HamburgerRepository,
        schedulerProvider: SchedulerProvider
) : UseCase<List<Hamburger>>(schedulerProvider) {
    fun getHamburgers(onNext: ((List<Hamburger>) -> Unit), onError: ((Throwable) -> Unit)) : Observable<List<Hamburger>>{
        return execute(hamburguerRepository.getHamburgers(), onNext, onError)
    }
}