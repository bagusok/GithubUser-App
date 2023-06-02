package com.bagusok.github.ui.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("html_url")
    val htmlUrl: String
)
