package com.picpay.desafio.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.ui.adapter.viewHolder.UserItemViewHolder

class UserAdapter : RecyclerView.Adapter<UserItemViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.count()
}