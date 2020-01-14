package com.crown.musicapp.di

import com.crown.musicapp.ui.ListingActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: ListingActivity)

}