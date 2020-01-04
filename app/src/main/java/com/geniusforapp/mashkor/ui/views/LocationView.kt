package com.geniusforapp.mashkor.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.geniusforapp.mashkor.R
import kotlinx.android.synthetic.main.view_location.view.*

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
class LocationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        initViews()
        initAttrs(attrs, defStyleAttr)
    }

    private var title: String? = null
    private var description: String? = null

    var onSelectionClicked: (() -> Unit)? = null
    private var icon: Drawable? = null

    var isViewEnabled = true


    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.LocationView, defStyleAttr, 0)
        title = a.getString(R.styleable.LocationView_title)
        description = a.getString(R.styleable.LocationView_description)
        isViewEnabled = a.getBoolean(R.styleable.LocationView_isViewEnabled, false)
        icon = ContextCompat.getDrawable(
            context,
            a.getResourceId(R.styleable.LocationView_startIcon, R.drawable.pin)
        )
        a.recycle()
        refreshViewsData()
    }

    private fun refreshViewsData() {
        withTextTitle(title)
        withTextDescription(description)
        locationView.setOnClickListener {
            if (isViewEnabled) {
                onSelectionClicked?.let { it1 -> it1() }
            }
        }
        withStartIcon(icon)
    }

    fun withTextTitle(text: String?) {
        textTitle.text = text
    }

    fun withTextDescription(text: String?) {
        textDescription.text = text
    }

    fun withStartIcon(icon: Drawable?) {
        pickPin.setImageDrawable(icon)
    }


    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.view_location, this, true)
    }

}