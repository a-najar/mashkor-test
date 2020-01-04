package com.geniusforapp.mashkor.business.repositories


import android.location.Location
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/

@Suppress("FunctionName")
fun DistanceRepository(): DistanceRepository = DistanceRepositoryImplementer()

interface DistanceRepository {

    fun calculateDistance(locationOne: LatLng, locationTwo: LatLng): Single<Float>
}


class DistanceRepositoryImplementer : DistanceRepository {

    override fun calculateDistance(locationOne: LatLng, locationTwo: LatLng): Single<Float> {
        return createRxDistance(locationOne, locationTwo)
    }

    private fun createRxDistance(locationOne: LatLng, locationTwo: LatLng) =
        Single.fromCallable<Float> { distance(locationOne, locationTwo) }

    private fun distance(locationOne: LatLng, locationTwo: LatLng): Float {
        val firstLocation = createLocation(locationOne)
        val secondLocation = createLocation(locationTwo)
        return firstLocation.distanceTo(secondLocation)
    }

    private fun createLocation(location: LatLng): Location {
        return Location(location.longitude.plus(location.latitude).toString())
            .apply {
                latitude = location.latitude
                longitude = location.longitude
            }
    }

}