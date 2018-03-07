package com.example.pedroabinajm.data.repository.datasource

import javax.inject.Inject

class HamburgerDataSourceFactory @Inject constructor(){
    fun create() : HamburguerDataSource{
        return CloudHamburguersDataSource()
    }
}