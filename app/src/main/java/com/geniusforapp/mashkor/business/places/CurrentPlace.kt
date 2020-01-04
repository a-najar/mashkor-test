package com.geniusforapp.mashkor.business.places

import com.geniusforapp.mashkor.data.places.CurrentPlace
import com.google.android.libraries.places.api.model.Place
import io.reactivex.Single

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class CurrentPlace(private val currentPlacePlace: CurrentPlace = CurrentPlace()) : () -> Single<Place> {
    override fun invoke(): Single<Place> {
        return currentPlacePlace
    }
}