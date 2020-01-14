package com.crown.musicapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crown.musicapp.R
import com.crown.musicapp.data.models.MusicDataModel
import com.crown.musicapp.utils.Utils
import kotlinx.android.synthetic.main.activity_album_detail.*
import kotlinx.android.synthetic.main.music_item_row.view.*

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var _context: Context
//    private lateinit v

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        getIntentData()
    }

    private fun getIntentData() {
        if(intent != null){
            setData()
        }
    }

    private fun setData() {
        val dataItem : MusicDataModel = intent.getParcelableExtra(Utils.EXTRA_DATA) as MusicDataModel
        tvArtistName.text = dataItem.artistName
        tvTrackName.text = dataItem.trackName
        tvCollectionName.text = dataItem.collectionName
        Glide.with(this).applyDefaultRequestOptions(
            RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
        ).load(dataItem.artworkUrl100).into(ivAlbumLogo)
    }
}
