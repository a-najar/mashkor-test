package com.geniusforapp.mashkor.data.places

import com.geniusforapp.mashkor.MashkorApplication
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import io.reactivex.Single
import io.reactivex.SingleObserver

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class CurrentPlace(
    private val placesClient: PlacesClient = Places.createClient(MashkorApplication.applicationContext),
    private val placesFiledRequests: FindCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(
        arrayListOf(
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.ID,
            Place.Field.LAT_LNG
        )
    )
) : Single<Place>() {
    override fun subscribeActual(observer: SingleObserver<in Place>) {
        placesClient.findCurrentPlace(placesFiledRequests).addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result != null) {
                    observer.onSuccess(it.result!!.placeLikelihoods.first().place)
                }
            } else {
                observer.onError(it.exception ?: IllegalArgumentException())
            }
        }
    }

}

