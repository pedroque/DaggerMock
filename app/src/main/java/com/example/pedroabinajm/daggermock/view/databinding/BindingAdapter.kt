package com.example.pedroabinajm.daggermock.view.databinding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import com.example.pedroabinajm.daggermock.R
import com.example.pedroabinajm.daggermock.extensions.friendlyMessage
import com.example.pedroabinajm.daggermock.extensions.hide
import com.example.pedroabinajm.daggermock.extensions.show

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("showIf")
    fun showIf(view: View, show: Boolean) {
        if (show) view.show() else view.hide()
    }

    @JvmStatic
    @BindingAdapter("errorText")
    fun errorText(textView: TextView, throwable: Throwable?) {
        throwable?.let {
            textView.setText(it.friendlyMessage)
        } ?: textView.setText(R.string.unexpected_error)
    }
}