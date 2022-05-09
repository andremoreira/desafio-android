package com.picpay.desafio.android.network.users.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("img") val img: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("id") val id: Int? = null,
    @SerializedName("username") val username: String? = ""
) : Parcelable