package com.bagusok.github.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagusok.github.ui.api.Client
import com.bagusok.github.ui.data.model.User
import com.bagusok.github.ui.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query: String ) {
        Client.apiInstance
            .getSearch(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("failed: ", t.message.toString())
                }

            })
    }

    fun getSearchUser(): LiveData<ArrayList<User>> {
        return listUsers
    }

}