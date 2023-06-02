package com.bagusok.github.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bagusok.github.ui.api.Client
import com.bagusok.github.ui.data.model.DataFavoriteUser
import com.bagusok.github.ui.data.model.UserDetail
import com.bagusok.github.ui.db.FavoriteUserDao
import com.bagusok.github.ui.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<UserDetail>()
    private var userDao: FavoriteUserDao? = null
    private var userDb: UserDB? = null

    init {
        userDb = UserDB.getDatabase(application)
        userDao = userDb?.favoriteUser()
    }

    fun setUser(username: String?) {
        Client.apiInstance
            .userDetail(username)
            .enqueue(object : Callback<UserDetail> {
                override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                    Log.d("Err: ", t.message.toString())
                }

            })
    }

    fun getUser(): LiveData<UserDetail> {
        return user
    }

    fun addToFavorite(id: Int, username: String?, imgUrl: String?, htmUrl: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = DataFavoriteUser(
                id,
                username,
                imgUrl,
                htmUrl
            )
            userDao?.addFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkFavorite(id)
    fun removeFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFavorite(id)
        }
    }
}