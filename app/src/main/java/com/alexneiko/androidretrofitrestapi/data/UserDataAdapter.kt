package com.alexneiko.androidretrofitrestapi.data

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.alexneiko.androidretrofitrestapi.App
import com.alexneiko.androidretrofitrestapi.R
import com.alexneiko.androidretrofitrestapi.model.UserAdapter
import com.alexneiko.androidretrofitrestapi.model.UserDTO
import com.alexneiko.androidretrofitrestapi.retrofit.Common
import com.alexneiko.androidretrofitrestapi.retrofit.RetrofitService
import com.alexneiko.androidretrofitrestapi.retrofit.Users
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


public class UserDataAdapter {


    //inject context
    @Inject
    lateinit var app: App
    var context : Context = App.instance.applicationContext

    //DTO list
    var userList = mutableListOf<UserDTO>()

    //net
    lateinit var mService : RetrofitService

    //local db worker
    var localUserDataStorageSPImpl = LocalUserDataStorageSPImpl(context)





    public fun getUsers(): List<UserDTO> {

        //net
        mService = Common.retrofitService

        mService.getUserList().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {


                for(i in 0 until response.body()?.data?.size!!) {
                    userList.add(
                        UserDTO(
                            response.body()?.data?.get(i)?.id,
                                response.body()?.data?.get(i)?.avatar,
                            response.body()?.data?.get(i)?.first_name,
                            response.body()?.data?.get(i)?.last_name,
                            response.body()?.data?.get(i)?.email
                    ))
                }
                localUserDataStorageSPImpl.saveUsersToDB(userList)
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                userList = localUserDataStorageSPImpl.getUsersFromDB().toMutableList()
            }
        })
        return userList
    }


}