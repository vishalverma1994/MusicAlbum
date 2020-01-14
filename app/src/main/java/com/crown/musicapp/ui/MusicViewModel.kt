package com.crown.musicapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.crown.musicapp.data.MusicDataRepository
import com.crown.musicapp.data.models.MusicDataModel
import com.crown.musicapp.data.models.MusicResponse
import javax.inject.Inject

class MusicViewModel @Inject constructor(private val musicRepo: MusicDataRepository) :
    ViewModel() {

    var musicDataList: ArrayList<MusicDataModel>? = null

    fun getMusicData(): LiveData<MusicResponse> {

        return musicRepo.getMusicData()
    }

    fun setMusicData(musicData: ArrayList<MusicDataModel>) {
        this.musicDataList = musicData

    }
}

