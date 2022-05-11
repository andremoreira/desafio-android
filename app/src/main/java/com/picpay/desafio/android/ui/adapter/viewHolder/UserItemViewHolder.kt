package com.picpay.desafio.android.ui.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.helper.hideView
import com.picpay.desafio.android.helper.loadImage
import com.picpay.desafio.android.helper.showView
import com.picpay.desafio.android.network.users.models.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*


class UserItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.progressBar.showView()
        itemView.name.text = user.name ?: ""
        itemView.username.text = user.username ?: ""
        itemView.picture.loadImage(user.img, itemView.progressBar)
    }
}
