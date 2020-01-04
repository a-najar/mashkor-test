package com.geniusforapp.mashkor.resourcses

import android.util.Log

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/
object Logger {
    var TAG: String = "APP_LOG"

    @JvmStatic
    fun error(throwable: Throwable) {
        Log.e(TAG, throwable.printStackTrace().toString())
        throwable.printStackTrace()
        // add to crash tool here to send it to firebase or any other tool
    }

    @JvmStatic
    fun debug(message: String) {
        Log.d(TAG, message)
    }

    @JvmStatic
    fun warn(message: String) {
        Log.w(TAG, message)
    }


}