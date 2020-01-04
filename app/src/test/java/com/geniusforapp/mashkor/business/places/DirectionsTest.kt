package com.geniusforapp.mashkor.business.places

import com.geniusforapp.mashkor.business.repositories.DirectionsRepository
import com.geniusforapp.mashkor.data.models.google.directions.LocationResult
import com.geniusforapp.mashkor.data.models.google.directions.LocationResultWrapper
import com.geniusforapp.mashkor.data.models.google.directions.LocationsDirections
import com.geniusforapp.mashkor.data.models.google.directions.LocationsDirections.Route
import com.geniusforapp.mashkor.data.models.google.directions.LocationsDirections.Route.OverviewPolyline
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 */
class DirectionsTest {

    @Test
    fun `invoke() when call directions return routes as String`() {
        Directions(directionsRepository())
            .invoke(locationResult)
            .test()
            .assertValue(wrappe)
    }


    @Test
    fun `invoke() when call directions return empty routes throw NullPointerException`() {
        Directions(emptyDirectionsRepository())
            .invoke(locationResult)
            .test()
            .assertFailure(NullPointerException::class.java)
    }

}


private fun directionsRepository(): DirectionsRepository = mock {
    on {
        getDirections(
            locationResult.getOrigin(),
            locationResult.getDestination(),
            locationResult.getMovingMode()
        )
    } doReturn Single.just(
        LocationsDirections(
            routes = arrayListOf(
                Route(
                    overviewPolyline = OverviewPolyline(points = routeOverView)
                )
            )
        )
    )
}

private fun emptyDirectionsRepository(): DirectionsRepository = mock {
    on {
        getDirections(
            locationResult.getOrigin(),
            locationResult.getDestination(),
            locationResult.getMovingMode()
        )
    } doReturn Single.just(LocationsDirections())
}


private const val routeOverView = "any-data"
private val locationResult = LocationResult(origin = null, destination = null, mode = null)

private val wrappe = LocationResultWrapper(locationResult = locationResult, route = routeOverView)