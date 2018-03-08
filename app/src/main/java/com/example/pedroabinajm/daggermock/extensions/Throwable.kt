package com.example.pedroabinajm.daggermock.extensions

import com.example.pedroabinajm.daggermock.R
import java.io.IOException

val Throwable.friendlyMessage: Int
    get() = if (this is IOException) {
        R.string.network_error
    } else {
        R.string.unexpected_error
    }