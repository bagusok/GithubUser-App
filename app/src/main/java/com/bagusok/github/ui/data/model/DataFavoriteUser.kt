package com.bagusok.github.ui.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "favorite_user")
data class DataFavoriteUser(
    @PrimaryKey
    var id: Int,

    var username: String?,
    var imgUrl: String?,
    var htmUrl: String?
): Serializable
