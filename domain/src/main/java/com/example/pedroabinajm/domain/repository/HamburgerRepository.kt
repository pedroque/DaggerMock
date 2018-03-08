package com.example.pedroabinajm.domain.repository

import com.example.pedroabinajm.domain.Hamburger
import io.reactivex.Observable

interface HamburgerRepository {
    fun getHamburgers() : Observable<List<Hamburger>>
}