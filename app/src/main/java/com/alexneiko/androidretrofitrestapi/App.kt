package com.alexneiko.androidretrofitrestapi

import android.app.Application
import com.alexneiko.androidretrofitrestapi.di.AppComponent
import com.alexneiko.androidretrofitrestapi.di.AppModule
import com.alexneiko.androidretrofitrestapi.di.DaggerAppComponent


//Created 25.01.2021 by Alex Neiko

class App : Application() {

   var name: String? = null
   get() =  resources.getString(R.string.app_name)
   private set


   private lateinit var appComponent: AppComponent




   override fun onCreate() {
      super.onCreate()
      instance = this

      appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

   }

   companion object {
      lateinit var instance: App
      private set
   }
}