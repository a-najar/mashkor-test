package com.geniusforapp.mashkor.ui.ktx

import android.view.View

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/


fun View.gone() {
    visibility = View.GONE
}


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


fun hideViews(vararg view: View) {
    view.forEach { it.gone() }
}

fun showViews(vararg view: View) {
    view.forEach { it.visible() }
}

fun View.enabled() {
    isEnabled = true
}