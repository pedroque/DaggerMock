package com.example.pedroabinajm.daggermock.view.hamburgers

import android.app.Activity
import android.content.res.Configuration
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.pedroabinajm.daggermock.BaseApp
import com.example.pedroabinajm.daggermock.Matchers.withRecyclerView
import com.example.pedroabinajm.daggermock.MockAndroidInjector
import com.example.pedroabinajm.daggermock.OrientationChangeAction.Companion.orientationLandscape
import com.example.pedroabinajm.daggermock.OrientationChangeAction.Companion.orientationPortrait
import com.example.pedroabinajm.daggermock.R
import com.example.pedroabinajm.daggermock.mapper.HamburgerMapper
import com.example.pedroabinajm.daggermock.schedulers.AndroidTestScheduleProvider
import com.example.pedroabinajm.daggermock.viewmodel.ViewModelFactory
import com.example.pedroabinajm.domain.Hamburger
import com.example.pedroabinajm.domain.interactor.GetHamburgers
import com.example.pedroabinajm.domain.repository.HamburgerRepository
import io.reactivex.Observable
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

@LargeTest
@RunWith(AndroidJUnit4::class)
class HamburgersActivityTest {
    @Rule
    @JvmField
    val activityRule = object : ActivityTestRule<HamburgersActivity>(HamburgersActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val myApp = InstrumentationRegistry.getTargetContext().applicationContext as BaseApp
            myApp.activityDispatchingAndroidInjector = MockAndroidInjector.activity<HamburgersActivity> {
                viewModelFactory = ViewModelFactory(
                        GetHamburgers(hamburgersRepository, AndroidTestScheduleProvider()),
                        HamburgerMapper()
                )
            }
        }
    }

    @Mock
    private lateinit var hamburgersRepository: HamburgerRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun hamburgersTest() {
        // Mock hamburgers
        val hamburgers = listOf(
                Hamburger("Pedro Burger", 5f, "Rua Luís Góis, 206")
        )
        Mockito.`when`(hamburgersRepository.getHamburgers())
                .thenReturn(Observable.just(hamburgers))

        // Launch activity
        activityRule.launchActivity(null)

        // Assert values
        onView(withRecyclerView(R.id.hamburgers_recycler).atPosition(0))
                .check(matches(hasDescendant(withText("Pedro Burger"))))

        rotateScreen(activityRule.activity)

        onView(withRecyclerView(R.id.hamburgers_recycler).atPosition(0))
                .check(matches(hasDescendant(withText("Pedro Burger"))))
    }

    @Test
    fun hamburgersNetworkErrorTest() {
        // Mock network error
        Mockito.`when`(hamburgersRepository.getHamburgers())
                .thenReturn(Observable.error(IOException()))

        // Launch activity
        activityRule.launchActivity(null)

        // Assert values
        onView(withId(R.id.error_text))
                .check(matches(allOf(withText(R.string.network_error), isDisplayed())))

        rotateScreen(activityRule.activity)

        onView(withId(R.id.error_text))
                .check(matches(allOf(withText(R.string.network_error), isDisplayed())))
    }

    @Test
    fun hamburgersUnexpectedErrorTest() {
        // Mock network error
        Mockito.`when`(hamburgersRepository.getHamburgers())
                .thenReturn(Observable.error(Throwable()))

        // Launch activity
        activityRule.launchActivity(null)

        // Assert values
        onView(withId(R.id.error_text))
                .check(matches(allOf(withText(R.string.unexpected_error), isDisplayed())))

        rotateScreen(activityRule.activity)

        onView(withId(R.id.error_text))
                .check(matches(allOf(withText(R.string.unexpected_error), isDisplayed())))
    }

    private fun rotateScreen(activity: Activity) {
        val orientation = InstrumentationRegistry.getTargetContext()
                .resources
                .configuration
                .orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            onView(isRoot()).perform(orientationLandscape(activity))
        else
            onView(isRoot()).perform(orientationPortrait(activity))
    }
}