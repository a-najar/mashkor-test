package com.geniusforapp.mashkor.business.init


import com.geniusforapp.mashkor.resourcses.CheckPermissions
import com.geniusforapp.mashkor.resourcses.NetworkValidator
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/

class Initialization(
    private val isPermissionGranted: () -> Single<Boolean> = { CheckPermissions() },
    private val isNetworkConnected: () -> Single<Boolean> = { NetworkValidator() },
    private val timer: () -> Single<Screen> = {
        Single.timer(TIMER_VALUE, TimeUnit.MILLISECONDS)
            .map { Screen.MainScreen }
    }
) : () -> Single<Screen> {
    override fun invoke(): Single<Screen> {
        return isNetworkConnected()
            .flatMap { if (it) checkPermission() else Single.just(Screen.NetworkRequested) }

    }

    private fun checkPermission() =
        isPermissionGranted().flatMap { if (it) timer() else Single.just(Screen.RequestPermission) }
}

private const val TIMER_VALUE = 1500L

sealed class Screen {
    object MainScreen : Screen()
    object RequestPermission : Screen()
    object NetworkRequested : Screen()
}