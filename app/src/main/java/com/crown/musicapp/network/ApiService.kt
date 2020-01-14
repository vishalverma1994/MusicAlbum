package com.crown.musicapp.network

import com.crown.musicapp.data.models.MusicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    companion object{
        const val  BASE_URL="https://itunes.apple.com/"
        const val singerName="Michael+jackson"

    }
    @GET("search?")
    fun getMusicData(@Query("term")id:String=singerName):
            Call<MusicResponse>
}