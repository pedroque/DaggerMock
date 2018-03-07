package com.example.pedroabinajm.daggermock.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.pedroabinajm.daggermock.mapper.HamburgerMapper
import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.domain.interactor.GetHamburgers

class HamburgersViewModel constructor(
        private val getHamburgers: GetHamburgers,
        private val hamburgerMapper: HamburgerMapper
) : ViewModel() {
    val hamburgers = MutableLiveData<List<HamburgerModel>>()

    fun fetchHamburgers() {
        hamburgers.postValue(getHamburgers.getHamburgers().map { hamburgerMapper.transform(it) })
    }
}