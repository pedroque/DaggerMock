package com.example.pedroabinajm.daggermock.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.pedroabinajm.daggermock.mapper.HamburgerMapper
import com.example.pedroabinajm.domain.interactor.GetHamburgers
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
        private val getHamburgers: GetHamburgers,
        private val hamburgerMapper: HamburgerMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HamburgersViewModel::class.java) -> HamburgersViewModel(getHamburgers, hamburgerMapper) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}