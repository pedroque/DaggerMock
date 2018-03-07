package com.example.pedroabinajm.daggermock.view.hamburgers

import com.example.pedroabinajm.daggermock.R
import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.daggermock.view.base.BaseAdapter

class HamburgersAdapter : BaseAdapter<HamburgerModel>() {

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_hamburger

}