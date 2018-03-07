package com.example.pedroabinajm.daggermock.model

import android.os.Parcel
import android.os.Parcelable

class HamburgerModel(
        val name: String,
        val rating: Float,
        val address: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readFloat(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeFloat(rating)
        writeString(address)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HamburgerModel> = object : Parcelable.Creator<HamburgerModel> {
            override fun createFromParcel(source: Parcel): HamburgerModel = HamburgerModel(source)
            override fun newArray(size: Int): Array<HamburgerModel?> = arrayOfNulls(size)
        }
    }
}