package com.bagusok.github.ui.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bagusok.github.ui.data.model.DataFavoriteUser

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun addFavorite(favoriteUser: DataFavoriteUser)

    @Query("SELECT * FROM favorite_user")
    fun getAllFavorite(): LiveData<List<DataFavoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkFavorite(id: Int): Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeFavorite(id: Int): Int


}