package com.geniusforapp.mashkor.business.repositories

import com.geniusforapp.mashkor.data.models.google.directions.LocationsDirections
import com.geniusforapp.mashkor.data.network.GoogleMapApi
import io.reactivex.Single

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/


@Suppress("FunctionName")
fun DirectionsRepositories(): DirectionsRepository = DirectionsRepositoryImplementer()

interface DirectionsRepository {
    fun getDirections(
        origin: String,
        destination: String,
        mode: String?
    ): Single<LocationsDirections>
}


class DirectionsRepositoryImplementer(private val googleMapApi: GoogleMapApi = GoogleMapApi()) :
    DirectionsRepository {
    override fun getDirections(
        origin: String,
        destination: String,
        mode: String?
    ): Single<LocationsDirections> = googleMapApi.getDirections(origin, destination, mode)
}


