package com.crown.musicapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crown.musicapp.data.models.MusicResponse
import com.crown.musicapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MusicDataRepository @Inject constructor(private val apiService: ApiService) {

    // hit the apis here
    fun getMusicData(): LiveData<MusicResponse> {
        val musicData: MutableLiveData<MusicResponse> = MutableLiveData()
        apiService.getMusicData().enqueue(object : Callback<MusicResponse> {
            override fun onResponse(
                call: Call<MusicResponse>,
                response: Response<MusicResponse?>
            ) {
                if (response.isSuccessful) {
                    musicData.value = response.body()
                } else {
                    musicData.value = null
                }
            }

            override fun onFailure(call: Call<MusicResponse>, t: Throwable?) {
                musicData.value = null
            }
        })
        return musicData

    }
}