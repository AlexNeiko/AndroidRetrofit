package com.alexneiko.androidretrofitrestapi.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component ( modules = [
    AppModule::class
])
interface AppComponent {

    fun context() : Context
}