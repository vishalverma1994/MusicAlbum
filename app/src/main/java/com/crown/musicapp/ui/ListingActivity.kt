package com.crown.musicapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.crown.musicapp.R
import com.crown.musicapp.base.MyApplication
import com.crown.musicapp.data.models.MusicDataModel
import com.crown.musicapp.data.models.MusicResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListingActivity : AppCompatActivity() {
    lateinit var musicViewModel: MusicViewModel

    /**
     * Injecting via dagger
     * Profit is that we don't need to create every different viewModelFactory class with different params
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val musicDataList = ArrayList<MusicDataModel>()
    private lateinit var recyclerAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musicViewModel =
            ViewModelProviders.of(this, viewModelFactory)[MusicViewModel::class.java]

        handleRotation(savedInstanceState)

        setupRecyclerView()
    }

    private fun handleRotation(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            // handling rotation
            musicViewModel.musicDataList?.apply {
                musicDataList.addAll(this)
            }

        }
    }

    private fun setupRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        rvMusicAlbum.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter(musicDataList)
        rvMusicAlbum.adapter = recyclerAdapter
        // for line separation between items
        val dividerItemDecoration =
            DividerItemDecoration(rvMusicAlbum.context, linearLayoutManager.orientation)
        rvMusicAlbum.addItemDecoration(dividerItemDecoration)
    }

    /*private fun getMusicData() {
        if (Utils.isOnline(this))
            musicViewModel.getMusicData().observe(
                this,
                Observer<MusicResponse> {
                    if (it != null) {
                        musicViewModel.setMusicData(it.musicDataList)
                        populateBottomSheet(it.list)
                    } else showErrorScreen(true)
                })
        else showErrorScreen(true, offline = true)

    }*/
}
