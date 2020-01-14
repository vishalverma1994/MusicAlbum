package com.crown.musicapp.base

import android.app.Application
import com.crown.musicapp.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent = DaggerAppComponent.create()

}