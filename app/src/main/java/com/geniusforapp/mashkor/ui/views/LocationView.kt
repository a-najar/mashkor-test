package com.geniusforapp.mashkor.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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


    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.LocationView)
        title = a.getString(R.styleable.LocationView_title)
        description = a.getString(R.styleable.LocationView_description)
        a.recycle()
        refreshViewsData()
    }

    private fun refreshViewsData() {
        withTextTitle(title)
        withTextDescription(description)
        locationView.setOnClickListener { onSelectionClicked?.let { it1 -> it1() } }
    }

    fun withTextTitle(text: String?) {
        textTitle.text = text
    }

    fun withTextDescription(text: String?) {
        textDescription.text = text
    }

    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.view_location, this, true)
    }

}