package com.geniusforapp.mashkor.business.init

import io.reactivex.Single
import org.junit.Test

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 */
class InitializationTest {


    @Test
    fun `invoke when invoke called should return MainScreen`() {
        Initialization(timer = { Single.just(Screen.MainScreen) })()
            .test()
            .assertValue(Screen.MainScreen)
    }

}