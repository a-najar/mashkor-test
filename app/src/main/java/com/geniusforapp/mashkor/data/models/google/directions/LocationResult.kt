package com.geniusforapp.mashkor.data.models.google.directions

import android.os.Parcelable
import com.google.android.libraries.places.api.model.Place
import kotlinx.android.parcel.Parcelize

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/


@Parcelize
class LocationResult(
    var origin: Place? = null,
    var destination: Place? = null,
    var mode: Mode? = null
) : Parcelable {

    fun getOrigin(): String =
        String.format("%s,%s", origin?.latLng?.latitude, origin?.latLng?.longitude)


    fun getDestination(): String =
        String.format("%s,%s", destination?.latLng?.latitude, destination?.latLng?.longitude)


    fun getMovingMode(): String? = mode?.value?.let { Mode.find(it).type }
}


data class LocationResultWrapper(
    val locationResult: LocationResult? = null,
    val route: String? = null
)


@Parcelize
enum class Mode(val value: Int, val type: String) : Parcelable {
    WALKING(1, "walking"),
    BUS(2, "bus"),
    TRAIN(3, "train"),
    CAR(4, "car"),
    NONE(-1, "none");

    companion object {
        fun find(id: Int): Mode = values().find { it.value == id } ?: NONE
    }
}