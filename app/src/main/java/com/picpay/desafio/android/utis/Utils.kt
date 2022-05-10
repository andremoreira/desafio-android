package com.picpay.desafio.android.utis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.service.autofill.OnClickAction
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import kotlinx.android.synthetic.main.activity_main.*

class Utils {

    companion object {
        fun isInternetAvailable(context: Context?): Boolean {
            var isConnected = false
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }
}