package com.geniusforapp.mashkor.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geniusforapp.mashkor.business.init.Initialization
import com.geniusforapp.mashkor.business.init.Screen
import com.geniusforapp.mashkor.resourcses.Logger
import com.geniusforapp.mashkor.resourcses.withThreading
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class SplashViewModel(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val initialization: () -> Single<Screen> = {
        Initialization()()
            .withThreading()
    }
) : ViewModel() {

    private val _showScreen: MutableLiveData<Screen> = MutableLiveData()


    val showScreen: LiveData<Screen> = _showScreen


    fun splashInit() {
        initialization()
            .subscribe(_showScreen::postValue, Logger::error)
            .also { compositeDisposable.add(compositeDisposable) }
    }

}


