package com.alexneiko.androidretrofitrestapi.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("users")
    fun getUserList(): Call<Users>
}