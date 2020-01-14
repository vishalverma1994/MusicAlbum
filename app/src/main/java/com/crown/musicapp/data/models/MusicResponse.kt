package com.crown.musicapp.data.models

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("timezone")
    val resultCount: Int,
    @SerializedName("results")
    val musicDataList: List<MusicDataModel>
)