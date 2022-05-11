package com.picpay.desafio.android.helper

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.picpay.desafio.android.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(name: String?, progressBar: ProgressBar) {
    progressBar.showView()
    Picasso.get()
        .load(name)
        .error(R.drawable.ic_round_account_circle)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.hideView()
            }

            override fun onError(e: Exception?) {
                progressBar.hideView()
            }
        })
}

