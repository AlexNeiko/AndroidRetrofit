package com.alexneiko.androidretrofitrestapi.retrofit

object Common {
    private val BASE_URL = "https://reqres.in/api/"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}