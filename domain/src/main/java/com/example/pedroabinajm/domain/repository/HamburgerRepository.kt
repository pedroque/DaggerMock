package com.example.pedroabinajm.domain.repository

import com.example.pedroabinajm.domain.Hamburger

interface HamburgerRepository {
    fun getHamburgers() : List<Hamburger>
}