package com.example.pedroabinajm.data.repository.datasource

import com.example.pedroabinajm.domain.Hamburger
import io.reactivex.Observable

interface HamburguerDataSource {
    fun getHamburguers() : Observable<List<Hamburger>>
}