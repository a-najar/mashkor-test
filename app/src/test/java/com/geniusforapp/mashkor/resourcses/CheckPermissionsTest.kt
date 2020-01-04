package com.geniusforapp.mashkor.resourcses

import org.junit.Test

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 */
class CheckPermissionsTest {


    @Test
    fun `checkPermission when permissions granted should return true`() {
        CheckPermissions(permissions, granted = { arrayListOf() })
            .test().assertValue(true)
    }

    @Test
    fun `checkPermission when permissions denied should return true`() {
        CheckPermissions(permissions, granted = { arrayListOf(false, false) })
            .test().assertValue(false)
    }
}


private val permissions = arrayOf("", "")


