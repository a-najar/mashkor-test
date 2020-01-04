package com.geniusforapp.mashkor.ui.selector

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.geniusforapp.mashkor.R
import com.geniusforapp.mashkor.data.models.google.directions.LocationResult
import com.geniusforapp.mashkor.data.models.google.directions.Mode
import com.geniusforapp.mashkor.ui.ktx.visible
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.content_locations_selector.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class LocationsSelectorActivity : AppCompatActivity() {

    companion object {
        private const val SELECT_ORIGIN = 2000
        private const val SELECT_DESTINATION = 3000
        fun getIntent(context: Context) = Intent(context, LocationsSelectorActivity::class.java)

        fun getResult(data: Intent?) =
            data?.getParcelableExtra<LocationResult>(LocationResult::class.java.simpleName)
    }


    private val viewModel: LocationSelectorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations_selector)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initObserver()
        initDistanceObserver()
        addViewListeners()


    }

    private fun initObserver() {
        viewModel.locations.observe(this, Observer {
            updateOrigin(it)
            updateDestination(it)
        })
    }

    private fun initDistanceObserver() {
        viewModel.distance.observe(this, Observer {
            textDistanceTitle.visible()
            textDistanceTitle.text = getString(R.string.text_distance, it.toString())
        })
    }

    private fun addViewListeners() {
        pickupLocationView.onSelectionClicked = { showSearchForPlace(SELECT_ORIGIN) }
        deliveryLocationView.onSelectionClicked = { showSearchForPlace(SELECT_DESTINATION) }
        chipsGroup.setOnCheckedChangeListener { _, i -> viewModel.updateMode(Mode.find(i)) }
    }


    private fun updateOrigin(locationResult: LocationResult) {
        locationResult.origin?.let {
            pickupLocationView.withTextTitle(it.name)
            pickupLocationView.withTextDescription(it.address)
        }
    }

    private fun updateDestination(locationResult: LocationResult) {
        locationResult.destination?.let {
            deliveryLocationView.withTextTitle(it.name)
            deliveryLocationView.withTextDescription(it.address)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            SELECT_ORIGIN -> viewModel.updateOrigin(Autocomplete.getPlaceFromIntent(data!!))
            SELECT_DESTINATION -> viewModel.updateDestination(Autocomplete.getPlaceFromIntent(data!!))
        }

    }

    fun onConfirm(view: View) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra(LocationResult::class.java.simpleName, viewModel.confirm())
        )
        onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearchForPlace(requestCode: Int) {
        val placesIntent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY, arrayListOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.ID,
                Place.Field.LAT_LNG
            )
        ).build(this)
        startActivityForResult(placesIntent, requestCode)
    }

}


