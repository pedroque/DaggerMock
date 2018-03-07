package com.example.pedroabinajm.daggermock.view.hamburgers

import com.example.pedroabinajm.daggermock.di.ActivityScope
import com.example.pedroabinajm.daggermock.mapper.HamburgerMapper
import com.example.pedroabinajm.daggermock.viewmodel.ViewModelFactory
import com.example.pedroabinajm.data.repository.HamburgerRepositoryImpl
import com.example.pedroabinajm.domain.interactor.GetHamburgers
import com.example.pedroabinajm.domain.repository.HamburgerRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
open class HamburgersModule {

    @Provides
    @Reusable
    internal fun provideHamburgerRepository(hamburgerRepository: HamburgerRepositoryImpl)
            : HamburgerRepository = hamburgerRepository

    @Provides
    @ActivityScope
    internal fun provideViewModelFactory(getHamburgers: GetHamburgers, hamburgerMapper: HamburgerMapper) =
            ViewModelFactory(getHamburgers, hamburgerMapper)

}