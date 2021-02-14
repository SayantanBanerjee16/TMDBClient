package com.sayantanbanerjee.tmdbclient.di

import android.content.Context
import dagger.Provides
import javax.inject.Singleton

class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}
