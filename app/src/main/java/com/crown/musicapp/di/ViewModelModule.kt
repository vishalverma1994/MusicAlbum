package com.crown.musicapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crown.musicapp.ui.MusicViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MusicViewModel::class)
    internal abstract fun postListViewModel(viewModel: MusicViewModel): ViewModel

    //Add more ViewModels here
}