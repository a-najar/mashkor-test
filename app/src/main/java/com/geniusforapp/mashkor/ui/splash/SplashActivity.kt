package com.geniusforapp.mashkor.ui.splash

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.geniusforapp.mashkor.R
import com.geniusforapp.mashkor.business.init.Screen
import com.geniusforapp.mashkor.resourcses.grantLocationPermissions
import com.geniusforapp.mashkor.ui.main.MainActivity


class SplashActivity : AppCompatActivity() {


    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.splashInit()
        splashViewModel.showScreen.observe(this, Observer {
            when (it) {
                is Screen.MainScreen -> showMainScreen()
                is Screen.RequestPermission -> showPermissionsDialog()
                is Screen.NetworkRequested -> showNetworkDialog()
            }
        })

    }

    private fun showNetworkDialog() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(R.string.dialog_title_network))
            .setMessage(getString(R.string.dialog_message_network))
            .setPositiveButton(getString(R.string.dialog_action_network))
            { _, _ -> openSettings() }
            .setNegativeButton(getString(R.string.dialog_action_cancel))
            { _, i -> finish() }
            .show()
    }

    private fun openSettings() {
        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    private fun showPermissionsDialog() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(R.string.dialog_title_permission))
            .setMessage(getString(R.string.dialog_message_permission))
            .setPositiveButton(getString(R.string.dialog_action_grant))
            { _, _ -> grantLocationPermissions(this) }
            .setNegativeButton(getString(R.string.dialog_action_cancel))
            { _, i -> finish() }
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        splashViewModel.splashInit()
    }

    private fun showMainScreen() {
        startActivity(
            Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}