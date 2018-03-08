package com.example.pedroabinajm.daggermock.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.pedroabinajm.daggermock.mapper.HamburgerMapper
import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.daggermock.viewmodel.commons.Resource
import com.example.pedroabinajm.domain.Hamburger
import com.example.pedroabinajm.domain.interactor.GetHamburgers
import io.reactivex.Observable

class HamburgersViewModel constructor(
        private val getHamburgers: GetHamburgers,
        private val hamburgerMapper: HamburgerMapper
) : ViewModel() {
    val hamburgers = MutableLiveData<Resource<List<HamburgerModel>>>()

    fun fetchHamburgers(): Observable<List<Hamburger>> {
        hamburgers.postValue(Resource.loading(null))
        return getHamburgers.getHamburgers({
            val result = it.map { hamburgerMapper.transform(it) }
            hamburgers.postValue(Resource.success(result))
            hamburgers.postValue(Resource.completed(result))
        },{
            hamburgers.postValue(Resource.error(it))
        })
    }

    fun dispose(){
        getHamburgers.dispose()
    }
}