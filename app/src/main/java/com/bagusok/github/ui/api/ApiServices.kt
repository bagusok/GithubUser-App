package com.bagusok.github.ui.api


import com.bagusok.github.BuildConfig
import com.bagusok.github.ui.data.model.User
import com.bagusok.github.ui.data.model.UserDetail
import com.bagusok.github.ui.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val auth = BuildConfig.KEY

interface ApiServices {

    @GET("search/users")
    @Headers("Authorization: token $auth")
    fun getSearch(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $auth")
    fun userDetail(
        @Path("username") username: String?
    ): Call<UserDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $auth")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $auth")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}