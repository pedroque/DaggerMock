package com.example.pedroabinajm.data.repository

import com.example.pedroabinajm.data.repository.datasource.HamburgerDataSourceFactory
import com.example.pedroabinajm.domain.Hamburger
import com.example.pedroabinajm.domain.repository.HamburgerRepository
import javax.inject.Inject

class HamburgerRepositoryImpl @Inject constructor(
        private val hamburgerDataSourceFactory: HamburgerDataSourceFactory
) : HamburgerRepository{
    override fun getHamburgers(): List<Hamburger> {
        return hamburgerDataSourceFactory.create().getHamburguers()
    }
}