package com.example.pedroabinajm.daggermock.mapper

import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.domain.Hamburger
import javax.inject.Inject

class HamburgerMapper @Inject constructor() {
    fun transform(hamburger: Hamburger) : HamburgerModel{
        return HamburgerModel(
                hamburger.name,
                hamburger.rating,
                hamburger.address
        )
    }
}