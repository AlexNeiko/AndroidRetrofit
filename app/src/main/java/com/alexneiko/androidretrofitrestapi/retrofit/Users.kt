package com.alexneiko.androidretrofitrestapi.retrofit

class Users {
    var page : Int? = null
    var per_page : Int? = null
    var total : Int? = null
    var total_pages : Int? = null
    var data : MutableList<JsonUserDTO>? = null

}