package com.bagusok.github.ui.data.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val login: String,
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("html_url")
    val html_url: String,

    @SerializedName("followers_url")
    val followers_url: String,

    @SerializedName("following_url")
    val following_url: String,

    val location: String,
    val company: String,

    @SerializedName("public_repos")
    val publicRepos: String,

    val followers: String,
    val following: String,
    val name: String
)
