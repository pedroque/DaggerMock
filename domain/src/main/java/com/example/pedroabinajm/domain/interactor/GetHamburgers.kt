package com.example.pedroabinajm.domain.interactor

import com.example.pedroabinajm.domain.Hamburger
import com.example.pedroabinajm.domain.repository.HamburgerRepository
import javax.inject.Inject

class GetHamburgers @Inject() constructor(
        private val hamburguerRepository: HamburgerRepository
){
    fun getHamburgers() : List<Hamburger>{
        return hamburguerRepository.getHamburgers()
    }
}