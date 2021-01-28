package com.alexneiko.androidretrofitrestapi.data

import com.alexneiko.androidretrofitrestapi.model.UserDTO

interface LocalUserDataStorage {

    fun saveUsersToDB(userList: List<UserDTO>)

    fun getUsersFromDB() : List<UserDTO>
}