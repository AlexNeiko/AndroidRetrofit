package com.alexneiko.androidretrofitrestapi.ui.about

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexneiko.androidretrofitrestapi.App
import com.alexneiko.androidretrofitrestapi.R
import javax.inject.Inject

class AboutViewModel : ViewModel() {

    @Inject
    lateinit var app:App

    var context : Context = App.instance.applicationContext

    private val _text = MutableLiveData<String>().apply {
        value = context.getString(R.string.fragmentAboutTextView)

    }
    val text: LiveData<String> = _text
}