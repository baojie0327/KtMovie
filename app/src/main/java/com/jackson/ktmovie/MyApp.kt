package com.jackson.ktmovie

import android.app.Application

/**
 * Created by Lenovo on 2018/1/29.
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MyApp? = null
            private set
    }
}