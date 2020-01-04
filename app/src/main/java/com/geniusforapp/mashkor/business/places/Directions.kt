package com.geniusforapp.mashkor.business.places

import com.geniusforapp.mashkor.business.repositories.DirectionsRepositories
import com.geniusforapp.mashkor.business.repositories.DirectionsRepository
import com.geniusforapp.mashkor.data.models.google.directions.LocationResult
import com.geniusforapp.mashkor.data.models.google.directions.LocationResultWrapper
import io.reactivex.Single

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/

class Directions(private val directionsRepository: DirectionsRepository = DirectionsRepositories()) :
        (LocationResult) -> Single<LocationResultWrapper> {


    override fun invoke(locationResult: LocationResult): Single<LocationResultWrapper> {
        return directionsRepository.getDirections(
            locationResult.getOrigin(),
            locationResult.getDestination(),
            locationResult.getMovingMode()
        )
            .map { it.routes.orEmpty() }
            .map { it.firstOrNull() }
            .map { it.overviewPolyline }
            .map { it.points }
            .map { LocationResultWrapper(locationResult, it) }
    }
}