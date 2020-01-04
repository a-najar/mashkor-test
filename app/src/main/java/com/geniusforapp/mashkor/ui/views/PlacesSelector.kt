package com.geniusforapp.mashkor.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.geniusforapp.mashkor.R
import com.geniusforapp.mashkor.ui.ktx.showViews
import kotlinx.android.synthetic.main.view_places_selectro.view.*

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class PlacesSelector @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        initViews()
    }

    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.view_places_selectro, this, true)
    }


    fun updatePickupLocation(title: String?, description: String?) {
        titlePickup.text = title
        descriptionPickup.text = description
    }

    fun updateDeliveryLocation(title: String?, description: String?) {
        showViews(viewLink, titleDelivery, descriptionDelivery, divider, deliveryPin)
        titleDelivery.text = title
        descriptionDelivery.text = description
    }

    fun withDistance(text: String) {
        textDistance.text = text
    }
}