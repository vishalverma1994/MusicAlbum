package com.crown.musicapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crown.musicapp.R
import kotlinx.android.synthetic.main.activity_album_detail.*

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
        tvArtistName.text = ""
        tvTrackName.text = ""
        tvCollectionName.text = ""
    }
}
