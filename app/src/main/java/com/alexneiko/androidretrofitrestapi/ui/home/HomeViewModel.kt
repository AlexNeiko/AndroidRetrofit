package com.alexneiko.androidretrofitrestapi.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexneiko.androidretrofitrestapi.App
import com.alexneiko.androidretrofitrestapi.data.LocalUserDataStorageSPImpl
import com.alexneiko.androidretrofitrestapi.data.UserDataAdapter
import com.alexneiko.androidretrofitrestapi.model.UserDTO
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    @Inject
    lateinit var app: App

    var context : Context = App.instance.applicationContext

    private val _text = MutableLiveData<String>().apply {
        value =   "офлайн режим. Обновлено - " + LocalUserDataStorageSPImpl(context).getLastUpdateTime()

    }
    val text: LiveData<String> = _text


    //users from net or local DB
    private val userList = MutableLiveData<MutableList<UserDTO>>().apply {
        value = UserDataAdapter().getUsers() as MutableList<UserDTO>
    }
    val users: LiveData<MutableList<UserDTO>> = userList

}

