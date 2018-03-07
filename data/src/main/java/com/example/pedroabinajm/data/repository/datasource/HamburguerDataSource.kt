package com.example.pedroabinajm.data.repository.datasource

import com.example.pedroabinajm.domain.Hamburger

interface HamburguerDataSource {
    fun getHamburguers() : List<Hamburger>
}