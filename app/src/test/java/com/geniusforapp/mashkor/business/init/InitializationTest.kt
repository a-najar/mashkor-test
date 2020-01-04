package com.geniusforapp.mashkor.business.init

import io.reactivex.Single
import org.junit.Test

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 */
class InitializationTest {


    @Test
    fun `invoke when Initialization called should return MainScreen`() {
        Initialization(
            isNetworkConnected = { Single.just(true) },
            isPermissionGranted = { Single.just(true) },
            timer = { Single.just(Screen.MainScreen) })()
            .test()
            .assertValue(Screen.MainScreen)
    }

    @Test
    fun `invoke when Initialization and permissions not granted should return RequestPermission`() {
        Initialization(
            isNetworkConnected = { Single.just(true) },
            isPermissionGranted = { Single.just(false) },
            timer = { Single.just(Screen.MainScreen) })()
            .test()
            .assertValue(Screen.RequestPermission)
    }

    @Test
    fun `invoke when Initialization and network not enabled should return NetworkRequested`() {
        Initialization(
            isNetworkConnected = { Single.just(false) },
            isPermissionGranted = { Single.just(true) },
            timer = { Single.just(Screen.MainScreen) })()
            .test()
            .assertValue(Screen.NetworkRequested)
    }

}