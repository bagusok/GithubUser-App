package com.bagusok.github.ui.main.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bagusok.github.ui.data.model.DataFavoriteUser
import com.bagusok.github.ui.db.FavoriteUserDao
import com.bagusok.github.ui.db.UserDB

class FavoriteUserViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: FavoriteUserDao? = null
    private var userDb: UserDB? = null

    init {
        userDb = UserDB.getDatabase(application)
        userDao = userDb?.favoriteUser()
    }

    fun getAllFavoriteUser(): LiveData<List<DataFavoriteUser>>? {
        return userDao?.getAllFavorite()
    }
}