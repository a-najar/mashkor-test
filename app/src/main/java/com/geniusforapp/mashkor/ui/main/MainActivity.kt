package com.geniusforapp.mashkor.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.geniusforapp.mashkor.R
import com.geniusforapp.mashkor.ui.ktx.addMarker
import com.geniusforapp.mashkor.ui.ktx.generateTextMarker
import com.geniusforapp.mashkor.ui.ktx.initGoogleMap
import com.geniusforapp.mashkor.ui.ktx.visible
import com.geniusforapp.mashkor.ui.selector.LocationsSelectorActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.PolyUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val SELECT_LOCATION = 2000
    }

    private lateinit var googleMap: GoogleMap

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initGoogleMap(this)
        addRouteObserver()
        placeSelector.setOnClickListener {
            startActivityForResult(LocationsSelectorActivity.getIntent(this), SELECT_LOCATION)
        }
    }

    private fun addRouteObserver() {
        mainViewModel.updateSelectedLocation.observe(this, Observer {
            googleMap.clear()
            it.locationResult?.let { locationResult ->
                placeSelector.updatePickupLocation(
                    locationResult.origin?.name,
                    locationResult.origin?.address
                )
                placeSelector.updateDeliveryLocation(
                    locationResult.destination?.name,
                    locationResult.destination?.address
                )

                googleMap.addMarker(
                    locationResult.destination?.latLng!!,
                    generateTextMarker(this, locationResult.destination?.name, R.color.colorPrimary)
                )

                googleMap.addMarker(
                    locationResult.origin?.latLng!!,
                    generateTextMarker(this, locationResult.origin?.name, R.color.colorAccent)
                )
            }
            MapAnimator.getInstance().animateRoute(
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimary),
                googleMap,
                PolyUtil.decode(it.route)
            )
        })
    }

    private fun addCurrentPlaceObserver() {
        mainViewModel.currentLiveData.observe(this, Observer {
            placeSelector.visible()
            googleMap.addMarker(
                it.latLng!!,
                generateTextMarker(this, it.name, R.color.colorAccent)
            )
            placeSelector.updatePickupLocation(it.name, it.address)

        })
    }

    override fun onMapReady(listinerGoogleMap: GoogleMap) {
        googleMap = listinerGoogleMap
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
        addCurrentPlaceObserver()
    }


    fun onConfirm(view: View) {
        startActivityForResult(LocationsSelectorActivity.getIntent(this), SELECT_LOCATION)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode != SELECT_LOCATION) return
        LocationsSelectorActivity.getResult(data)?.let { mainViewModel.getDirections(it) }
        super.onActivityResult(requestCode, resultCode, data)
    }


}



