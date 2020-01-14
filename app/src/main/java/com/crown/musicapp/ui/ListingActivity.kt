package com.crown.musicapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.crown.musicapp.R
import com.crown.musicapp.base.MyApplication
import com.crown.musicapp.data.models.MusicDataModel
import com.crown.musicapp.data.models.MusicResponse
import com.crown.musicapp.utils.Utils
import com.crown.musicapp.utils.Utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListingActivity : AppCompatActivity() {

    private lateinit var musicViewModel: MusicViewModel
    private lateinit var mContext: Context

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
        mContext = this@ListingActivity
        musicViewModel =
            ViewModelProviders.of(this, viewModelFactory)[MusicViewModel::class.java]

        handleRotation(savedInstanceState)
        setupRecyclerView()
        getMusicData()
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

        val linearLayoutManager = LinearLayoutManager(mContext)
        rvMusicAlbum.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerAdapter(mContext, musicDataList, onClick)
        rvMusicAlbum.adapter = recyclerAdapter
        // for line separation between items
        rvMusicAlbum.addItemDecoration(
            DividerItemDecoration(
                rvMusicAlbum.context,
                linearLayoutManager.orientation
            )
        )
    }

    private fun getMusicData() {
        if (Utils.isOnline(this))
            musicViewModel.getMusicData().observe(
                this,
                Observer<MusicResponse> {
                    if (it != null) {
                        musicViewModel.setMusicData(it.musicDataList)
                        populateList(it.musicDataList)
                    }
//                    else showErrorScreen(true)
                })
//        else showErrorScreen(true, offline = true)

    }

    private fun populateList(list: ArrayList<MusicDataModel>) {
        if (list.size > 0) {
            rvMusicAlbum.visibility = View.VISIBLE
            tvNoData.visibility = View.GONE
        } else {
            rvMusicAlbum.visibility = View.GONE
            tvNoData.visibility = View.VISIBLE
        }
        recyclerAdapter.updateList(list)
    }

    private val onClick: (Int) -> Unit = {
        startActivity(Intent(mContext, AlbumDetailActivity::class.java).putExtra(EXTRA_DATA,musicViewModel.musicDataList!![it]))
    }
}
