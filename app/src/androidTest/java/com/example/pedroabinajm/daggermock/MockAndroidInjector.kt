package com.example.pedroabinajm.daggermock

import android.app.Activity
import android.support.test.InstrumentationRegistry
import android.support.v4.app.Fragment
import com.example.pedroabinajm.daggermock.view.base.BaseFragmentActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import javax.inject.Provider

object MockAndroidInjector{
    inline fun <reified T : Activity> activity(crossinline block : T.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is T) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(Pair<Class<out Activity>, Provider<AndroidInjector.Factory<out Activity>>>(T::class.java, Provider { factory }))
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
    }

    inline fun <reified F : Fragment, reified T: BaseFragmentActivity> fragment(crossinline block : F.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        var originalFragmentInjector: AndroidInjector<Fragment>? = null
        // obtain the application level injector
        val myApp = InstrumentationRegistry.getTargetContext().applicationContext as BaseApp
        val originalDispatchingActivityInjector = myApp.activityDispatchingAndroidInjector

        // set the fragment injector which apply the original injector and than apply our block
        val fragmentInjector = AndroidInjector<Fragment> { fragment ->
            originalFragmentInjector?.inject(fragment)
            if (fragment is F) {
                fragment.block()
            }
        }
        val fragmentFactory = AndroidInjector.Factory<Fragment> { fragmentInjector }
        val fragmentMap = mapOf(Pair<Class <out Fragment>, Provider<AndroidInjector.Factory<out Fragment>>>(F::class.java, Provider { fragmentFactory }))

        // obtain the activity injector
        val activityInjector = AndroidInjector<Activity> { activity ->
            originalDispatchingActivityInjector.inject(activity)
            if (activity is T ) {
                originalFragmentInjector = activity.fragmentDispatchingAndroidInjector
                activity.fragmentDispatchingAndroidInjector = DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(fragmentMap)
            }
        }
        val activityFactory = AndroidInjector.Factory<Activity> { activityInjector }
        val activityMap = mapOf(Pair<Class <out Activity>, Provider<AndroidInjector.Factory<out Activity>>>(T::class.java, Provider { activityFactory }))
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(activityMap)
    }
}