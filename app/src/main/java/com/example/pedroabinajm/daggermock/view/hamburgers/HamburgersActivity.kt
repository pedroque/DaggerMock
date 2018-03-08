package com.example.pedroabinajm.daggermock.view.hamburgers

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.pedroabinajm.daggermock.R
import com.example.pedroabinajm.daggermock.databinding.ActivityHamburgersBinding
import com.example.pedroabinajm.daggermock.model.HamburgerModel
import com.example.pedroabinajm.daggermock.view.base.BaseActivity
import com.example.pedroabinajm.daggermock.viewmodel.HamburgersViewModel
import com.example.pedroabinajm.daggermock.viewmodel.ViewModelFactory
import com.example.pedroabinajm.daggermock.viewmodel.commons.Resource
import javax.inject.Inject

class HamburgersActivity : BaseActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private var hamburgersViewModel: HamburgersViewModel? = null
    private lateinit var dataBinding: ActivityHamburgersBinding

    private var hamburgersAdapter: HamburgersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hamburgersViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HamburgersViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_hamburgers, null, false)
        setContentView(dataBinding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        dataBinding.hamburgersRecycler.setUp()
        hamburgersViewModel?.run {
            bind()
            if (hamburgers.value == null) {
                fetchHamburgers()
            }
        }
    }

    override fun onDestroy() {
        hamburgersViewModel?.dispose()
        super.onDestroy()
    }

    private fun RecyclerView.setUp() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@HamburgersActivity)
        hamburgersAdapter = HamburgersAdapter()
        adapter = hamburgersAdapter
    }

    private fun HamburgersViewModel.bind() {
        hamburgers.observe(this@HamburgersActivity, Observer<Resource<List<HamburgerModel>>> {
            dataBinding.resource = it
            it?.let {
                if (!it.isEmpty) hamburgersAdapter?.replace(it.data!!)
            }
        })
    }
}