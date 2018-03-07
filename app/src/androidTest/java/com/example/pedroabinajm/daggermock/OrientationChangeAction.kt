package com.example.pedroabinajm.daggermock

import android.app.Activity
import android.content.pm.ActivityInfo
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.view.View
import org.hamcrest.Matcher


class OrientationChangeAction private constructor(private val orientation: Int, private val activity: Activity) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return isRoot()
    }

    override fun getDescription(): String {
        return "change orientation to " + orientation
    }

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadUntilIdle()
        activity.requestedOrientation = orientation

        val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
        if (resumedActivities.isEmpty()) {
            throw RuntimeException("Could not change orientation")
        }
    }

    companion object {

        fun orientationLandscape(activity: Activity): ViewAction {
            return OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, activity)
        }

        fun orientationPortrait(activity: Activity): ViewAction {
            return OrientationChangeAction(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, activity)
        }
    }
}