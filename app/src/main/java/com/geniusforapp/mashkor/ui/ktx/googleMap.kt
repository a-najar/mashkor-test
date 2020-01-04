package com.geniusforapp.mashkor.ui.ktx

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.geniusforapp.mashkor.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/

fun AppCompatActivity.initGoogleMap(onMapReadyCallback: OnMapReadyCallback) {
    (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
        .apply {
            getMapAsync(onMapReadyCallback)
        }
}

private fun generateTextIcon(context: Context, color: Int, title: String?): Bitmap {
    return IconGenerator(context)
        .apply {
            setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
            setColor(ContextCompat.getColor(context, color))
        }
        .makeIcon(title)
}


fun generateTextMarker(context: Context, title: String?, color: Int): BitmapDescriptor {
    return BitmapDescriptorFactory.fromBitmap(generateTextIcon(context, color, title))
}

fun GoogleMap.addMarker(latLng: LatLng, icon: BitmapDescriptor) {
    addMarker(
        MarkerOptions()
            .position(latLng)
            .icon(icon)
    )
    animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))
}