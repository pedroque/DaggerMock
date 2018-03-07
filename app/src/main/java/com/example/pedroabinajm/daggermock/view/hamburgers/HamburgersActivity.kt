package com.example.pedroabinajm.daggermock.view.hamburgers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.pedroabinajm.daggermock.R
import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.daggermock.view.base.BaseActivity
import com.example.pedroabinajm.daggermock.viewmodel.HamburgersViewModel
import com.example.pedroabinajm.daggermock.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_hamburgers.*
import javax.inject.Inject

class HamburgersActivity : BaseActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var hamburgersViewModel: HamburgersViewModel
    private var hamburgersAdapter: HamburgersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hamburgersViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HamburgersViewModel::class.java)
        setContentView(R.layout.activity_hamburgers)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        hamburgers_recycler.setUp()
        hamburgersViewModel.bind()
        if( hamburgersViewModel.hamburgers.value == null) {
            hamburgersViewModel.fetchHamburgers()
        }
    }

    private fun RecyclerView.setUp() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@HamburgersActivity)
        hamburgersAdapter = HamburgersAdapter()
        adapter = hamburgersAdapter
    }

    private fun HamburgersViewModel.bind() {
        hamburgers.observe(this@HamburgersActivity, Observer<List<HamburgerModel>> {
            it?.let {
                hamburgersAdapter?.replace(it)
            }
        })
    }
}