package com.geniusforapp.mashkor.data.models.google.directions


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class LocationsDirections(
    @SerializedName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypoint?>? = null,
    @SerializedName("routes")
    val routes: List<Route?>? = null,
    @SerializedName("status")
    val status: String? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        val geocoderStatus: String? = null,
        @SerializedName("place_id")
        val placeId: String? = null,
        @SerializedName("types")
        val types: List<String?>? = null
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Route(
        @SerializedName("bounds")
        val bounds: Bounds? = null,
        @SerializedName("copyrights")
        val copyrights: String? = null,
        @SerializedName("legs")
        val legs: List<Leg?>? = null,
        @SerializedName("overview_polyline")
        val overviewPolyline: OverviewPolyline? = null,
        @SerializedName("summary")
        val summary: String? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Bounds(
            @SerializedName("northeast")
            val northeast: Northeast? = null,
            @SerializedName("southwest")
            val southwest: Southwest? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Northeast(
                @SerializedName("lat")
                val lat: Double? = null,
                @SerializedName("lng")
                val lng: Double? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Southwest(
                @SerializedName("lat")
                val lat: Double? = null,
                @SerializedName("lng")
                val lng: Double? = null
            ) : Parcelable
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Leg(
            @SerializedName("distance")
            val distance: Distance? = null,
            @SerializedName("duration")
            val duration: Duration? = null,
            @SerializedName("end_address")
            val endAddress: String? = null,
            @SerializedName("end_location")
            val endLocation: EndLocation? = null,
            @SerializedName("start_address")
            val startAddress: String? = null,
            @SerializedName("start_location")
            val startLocation: StartLocation? = null,
            @SerializedName("steps")
            val steps: List<Step?>? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Distance(
                @SerializedName("text")
                val text: String? = null,
                @SerializedName("value")
                val value: Int? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Duration(
                @SerializedName("text")
                val text: String? = null,
                @SerializedName("value")
                val value: Int? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class EndLocation(
                @SerializedName("lat")
                val lat: Double? = null,
                @SerializedName("lng")
                val lng: Double? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class StartLocation(
                @SerializedName("lat")
                val lat: Double? = null,
                @SerializedName("lng")
                val lng: Double? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Step(
                @SerializedName("distance")
                val distance: Distance? = null,
                @SerializedName("duration")
                val duration: Duration? = null,
                @SerializedName("end_location")
                val endLocation: EndLocation? = null,
                @SerializedName("html_instructions")
                val htmlInstructions: String? = null,
                @SerializedName("maneuver")
                val maneuver: String? = null,
                @SerializedName("polyline")
                val polyline: Polyline? = null,
                @SerializedName("start_location")
                val startLocation: StartLocation? = null,
                @SerializedName("travel_mode")
                val travelMode: String? = null
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Distance(
                    @SerializedName("text")
                    val text: String? = null,
                    @SerializedName("value")
                    val value: Int? = null
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Duration(
                    @SerializedName("text")
                    val text: String? = null,
                    @SerializedName("value")
                    val value: Int? = null
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class EndLocation(
                    @SerializedName("lat")
                    val lat: Double? = null,
                    @SerializedName("lng")
                    val lng: Double? = null
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Polyline(
                    @SerializedName("points")
                    val points: String? = null
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class StartLocation(
                    @SerializedName("lat")
                    val lat: Double? = null,
                    @SerializedName("lng")
                    val lng: Double? = null
                ) : Parcelable
            }
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class OverviewPolyline(
            @SerializedName("points")
            val points: String? = null
        ) : Parcelable
    }
}