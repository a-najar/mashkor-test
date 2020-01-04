package com.geniusforapp.mashkor

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class MashkorApplication : Application() {

    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        MashkorApplication.applicationContext = this
        Places.initialize(applicationContext, BuildConfig.GOOGLE_API_KEY)
    }
}