package com.picpay.desafio.android.utis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


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