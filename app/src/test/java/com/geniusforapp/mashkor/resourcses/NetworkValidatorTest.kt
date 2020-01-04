package com.geniusforapp.mashkor.resourcses

import org.junit.Test

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 */
class NetworkValidatorTest {


    @Test
    fun `onSubscribe when network is connected return true`() {
        NetworkValidator(isNetWorkConnected = { true })
            .test()
            .assertValue(true)

    }


    @Test
    fun `onSubscribe when network is not connected return false`() {
        NetworkValidator(isNetWorkConnected = { false })
            .test()
            .assertValue(false)

    }

}