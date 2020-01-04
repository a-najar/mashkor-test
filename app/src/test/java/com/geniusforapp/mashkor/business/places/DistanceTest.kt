package com.geniusforapp.mashkor.business.places

import com.geniusforapp.mashkor.business.repositories.DistanceRepository
import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test
import kotlin.math.roundToInt

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 */
class DistanceTest {


    @Test
    fun `invoke() when locations is sent is sent return distance as Int`() {
        Distance(distanceRepository())
            .invoke(locationOne, locationTwo)
            .test()
            .assertValue(finalResult)
    }
}

private fun distanceRepository(): DistanceRepository =
    mock {
        on { calculateDistance(locationOne, locationTwo) } doReturn Single.just(distanceFloat)
    }

private const val distanceFloat = 1f
private var finalResult = (distanceFloat * 1.609344).roundToInt()
private val locationOne = LatLng(0.0, 0.0)
private val locationTwo = LatLng(0.0, 0.0)