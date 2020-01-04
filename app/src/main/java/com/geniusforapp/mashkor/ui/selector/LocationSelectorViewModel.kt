package com.geniusforapp.mashkor.ui.selector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geniusforapp.mashkor.business.places.Distance
import com.geniusforapp.mashkor.data.models.google.directions.LocationResult
import com.geniusforapp.mashkor.data.models.google.directions.Mode
import com.geniusforapp.mashkor.resourcses.Logger
import com.geniusforapp.mashkor.resourcses.withThreading
import com.google.android.libraries.places.api.model.Place
import io.reactivex.disposables.CompositeDisposable

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class LocationSelectorViewModel(private val distanceUseCase: Distance = Distance()) : ViewModel() {
    private val locationResult: LocationResult =
        LocationResult()

    private val compositeDisposable = CompositeDisposable()

    private val _locations: MutableLiveData<LocationResult> = MutableLiveData()
    private val _distance: MutableLiveData<Int> = MutableLiveData()

    val locations: LiveData<LocationResult> = _locations
    val distance: LiveData<Int> = _distance

    fun updateOrigin(place: Place) {
        locationResult.origin = place
        _locations.postValue(locationResult)
    }

    fun updateDestination(place: Place) {
        locationResult.destination = place
        _locations.postValue(locationResult)
        calculateDistance()
    }

    fun updateMode(mode: Mode) {
        locationResult.mode = mode
        _locations.postValue(locationResult)
    }

    fun confirm(): LocationResult {
        return locationResult
    }

    private fun calculateDistance() {
        distanceUseCase(locationResult.origin?.latLng!!, locationResult.destination?.latLng!!)
            .withThreading()
            .subscribe(_distance::postValue, Logger::error)
            .also { compositeDisposable.add(it) }
    }


}