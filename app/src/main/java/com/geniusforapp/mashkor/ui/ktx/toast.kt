package com.geniusforapp.mashkor.ui.ktx

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/
fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


fun AppCompatActivity.toast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


fun Fragment.toast(message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}


fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}