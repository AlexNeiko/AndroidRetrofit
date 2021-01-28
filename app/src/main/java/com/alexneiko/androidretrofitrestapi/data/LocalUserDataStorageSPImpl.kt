package com.alexneiko.androidretrofitrestapi.data

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.alexneiko.androidretrofitrestapi.R
import com.alexneiko.androidretrofitrestapi.model.UserDTO
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class LocalUserDataStorageSPImpl (private val context: Context) : LocalUserDataStorage {

    var userSharedPreferences : SharedPreferences? = context.getSharedPreferences(
        "users",
        Context.MODE_PRIVATE
    )
    var editor: Editor? = userSharedPreferences?.edit()




    override fun saveUsersToDB(userList: List<UserDTO>) {
        val gson = Gson()
        val usersListStr : String = gson.toJson(userList)
        editor?.putLong("updatetime", Calendar.getInstance().timeInMillis)
        editor?.putString("users", usersListStr)
        editor?.commit()
    }

    override fun getUsersFromDB() : List<UserDTO> {

        var userList = mutableListOf<UserDTO>()


        if (userSharedPreferences?.contains("users")!!) {
            val gson = Gson()
            val gsonStr: String? = userSharedPreferences?.getString("users", "")
            if ( gsonStr != "") {
                userList = gson.fromJson(gsonStr, Array<UserDTO>::class.java).toMutableList()
            }
        }

        userList.add(UserDTO(0, "avatar", "Offline", "Test", "dev1@.us"))

        return userList
    }


    fun getLastUpdateTime() : String {
        return if (userSharedPreferences?.contains("updatetime")!!) {
            val date = Date(userSharedPreferences?.getLong("updatetime", 0)!!)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
            format.format(date)
        }
        else {
            context.getString(R.string.noData)
        }
    }

}