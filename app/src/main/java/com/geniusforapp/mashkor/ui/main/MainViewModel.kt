package com.geniusforapp.mashkor.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geniusforapp.mashkor.business.places.CurrentPlace
import com.geniusforapp.mashkor.business.places.Directions
import com.geniusforapp.mashkor.data.models.google.directions.LocationResult
import com.geniusforapp.mashkor.data.models.google.directions.LocationResultWrapper
import com.geniusforapp.mashkor.resourcses.Logger
import com.geniusforapp.mashkor.resourcses.withThreading
import com.google.android.libraries.places.api.model.Place
import io.reactivex.disposables.CompositeDisposable

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class MainViewModel(
    private val currentPlaceLocationUseCase: CurrentPlace = CurrentPlace(),
    private val directionsUseCase: Directions = Directions()
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    val currentLiveData: LiveData<Place> = MutableLiveData<Place>().apply {
        currentPlaceLocationUseCase()
            .withThreading()
            .doOnSubscribe { loading.postValue(true) }
            .doOnSuccess { loading.postValue(false) }
            .subscribe(::postValue, Throwable::printStackTrace)
            .also { compositeDisposable.add(it) }

    }
    var updateSelectedLocation: MutableLiveData<LocationResultWrapper> = MutableLiveData()


    fun getDirections(locationResult: LocationResult) {
        loading.postValue(true)
        directionsUseCase(locationResult)
            .withThreading()
            .doOnSubscribe { loading.postValue(true) }
            .doOnSuccess { loading.postValue(false) }
            .subscribe(updateSelectedLocation::postValue, Logger::error)
            .also { compositeDisposable.add(it) }
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}