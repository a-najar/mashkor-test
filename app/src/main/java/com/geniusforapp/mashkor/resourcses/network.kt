package com.geniusforapp.mashkor.resourcses

import android.content.Context
import android.net.ConnectivityManager
import com.geniusforapp.mashkor.MashkorApplication
import io.reactivex.Single
import io.reactivex.SingleObserver


/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/
class NetworkValidator(private val isNetWorkConnected: () -> Boolean = { isConnected() }) :
    Single<Boolean>() {
    override fun subscribeActual(observer: SingleObserver<in Boolean>) {
        observer.onSuccess(isNetWorkConnected())
    }
}

fun isConnected(): Boolean {
    val cm =
        MashkorApplication.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null &&
            activeNetwork.isConnectedOrConnecting
}