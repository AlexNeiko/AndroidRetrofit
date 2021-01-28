package com.alexneiko.androidretrofitrestapi.di

import android.content.Context
import com.alexneiko.androidretrofitrestapi.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val app:App) {

    @Provides //transfer Context
    internal fun app():App{
        return app
    }

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = app
}