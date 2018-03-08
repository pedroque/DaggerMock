package com.example.pedroabinajm.daggermock.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.show() {
    toggleViewVisibilityAnimated(
            resources.getInteger(android.R.integer.config_shortAnimTime),
            ShowViewAnimatorListener(this),
            1f
    )
}

fun View.hide() {
    toggleViewVisibilityAnimated(
            resources.getInteger(android.R.integer.config_shortAnimTime),
            HideViewAnimatorListener(this),
            0f
    )
}

fun View.toggleViewVisibilityAnimated(duration: Int, listener: Animator.AnimatorListener, alpha: Float) {
    animate().alpha(alpha)
            .translationY(0f)
            .setDuration(duration.toLong())
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setListener(listener)
}

class HideViewAnimatorListener @JvmOverloads constructor(
        private val view: View,
        private val mHideState: Int = View.GONE
) : AnimatorListenerAdapter() {

    override fun onAnimationEnd(animation: Animator) {
        view.visibility = mHideState
        view.animate().setListener(null)
    }
}

class ShowViewAnimatorListener(
        private val view: View
) : AnimatorListenerAdapter() {

    override fun onAnimationStart(animation: Animator) {
        view.visibility = View.VISIBLE
    }

    override fun onAnimationEnd(animation: Animator) {
        view.animate().setListener(null)
    }
}