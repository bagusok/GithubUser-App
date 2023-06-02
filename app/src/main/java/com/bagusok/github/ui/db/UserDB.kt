package com.bagusok.github.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bagusok.github.ui.data.model.DataFavoriteUser

@Database(
    entities = [DataFavoriteUser::class],
    version = 1
)
abstract class UserDB : RoomDatabase() {

    companion object {
        var INSTANCE : UserDB? = null

        fun getDatabase(context: Context): UserDB? {
            if (INSTANCE == null) {
                synchronized(UserDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDB::class.java, "user_database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoriteUser() : FavoriteUserDao
}