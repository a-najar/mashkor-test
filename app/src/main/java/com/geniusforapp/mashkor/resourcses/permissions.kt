package com.geniusforapp.mashkor.resourcses

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.geniusforapp.mashkor.MashkorApplication
import io.reactivex.Single
import io.reactivex.SingleObserver

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/
class CheckPermissions(
    private val permissions: Array<out String> = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ),
    private val granted: () -> List<Boolean> = { checkPermissions(permissions) }
) : Single<Boolean>() {

    override fun subscribeActual(observer: SingleObserver<in Boolean>) {
        observer.onSuccess(granted().isEmpty())
    }
}

private fun checkPermissions(permissions: Array<out String>): List<Boolean> {
    return permissions
        .map { checkPermission(it) }
        .filter { !it }
}

private fun checkPermission(permission: String) =
    ActivityCompat.checkSelfPermission(MashkorApplication.applicationContext, permission) ==
            PackageManager.PERMISSION_GRANTED


fun grantLocationPermissions(activity: AppCompatActivity, code: Int = PERMISSION_REQUEST_CODE) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        code
    )
}


private const val PERMISSION_REQUEST_CODE: Int = 3030