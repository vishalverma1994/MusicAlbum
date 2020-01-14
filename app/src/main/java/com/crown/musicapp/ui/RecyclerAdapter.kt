package com.crown.musicapp.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crown.musicapp.R
import com.crown.musicapp.data.models.MusicDataModel
import kotlinx.android.synthetic.main.music_item_row.view.*
import java.util.*


class RecyclerAdapter(
    private val mContext: Context,
    private val musicDataList: ArrayList<MusicDataModel>,
    var mOnClick:(Int) -> Unit)
    : RecyclerView.Adapter<RecyclerAdapter.MusicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflatedView = parent.inflate(R.layout.music_item_row, false)
        return MusicViewHolder(inflatedView)
    }

    override fun getItemCount() = musicDataList.size

    override fun onBindViewHolder(viewHolder: MusicViewHolder, position: Int) {
        if (musicDataList.size > 0) {

            val itemMusic = musicDataList[position]
            viewHolder.bindItem(mContext, itemMusic)
        }
    }

    fun updateList(newData: ArrayList<MusicDataModel>) {
        musicDataList.clear()
        musicDataList.addAll(newData)
        notifyDataSetChanged()

    }


    inner class MusicViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private lateinit var musicData: MusicDataModel
        private var view: View = v

        init {
            v.setOnClickListener{mOnClick(adapterPosition)}
        }

        fun bindItem(mContext: Context, musicData: MusicDataModel) {
            this.musicData = musicData
            view.tvArtistName.text = musicData.artistName
            view.tvTrackName.text = musicData.trackName
            Glide.with(mContext).applyDefaultRequestOptions(
                RequestOptions().error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            ).load(musicData.artworkUrl100).into(view.ivAlbumLogo)
        }
    }
}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

