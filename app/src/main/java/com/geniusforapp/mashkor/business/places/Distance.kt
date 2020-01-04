package com.geniusforapp.mashkor.business.places

import com.geniusforapp.mashkor.business.repositories.DistanceRepository
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import kotlin.math.roundToInt

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/
class Distance(private val distanceRepository: DistanceRepository = DistanceRepository()) :
        (LatLng, LatLng) -> Single<Int> {

    override fun invoke(locationOne: LatLng, locationTwo: LatLng): Single<Int> {
        return distanceRepository.calculateDistance(locationOne, locationTwo)
            .map { it.convertToKm() }
    }

    private fun Float.convertToKm(): Int {
        return (this * 0.001).roundToInt()
    }
}


