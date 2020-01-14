package com.crown.musicapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.crown.musicapp.R
import com.crown.musicapp.data.models.MusicDataModel
import java.util.*


class RecyclerAdapter(private val musicDataList: ArrayList<MusicDataModel>) : RecyclerView.Adapter<RecyclerAdapter.MusicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflatedView = parent.inflate(R.layout.music_item_row, false)
        return MusicViewHolder(inflatedView)
    }

    override fun getItemCount() = musicDataList.size

    override fun onBindViewHolder(viewHolder: MusicViewHolder, position: Int) {
        if (musicDataList.size > 0) {

            val itemMusic = musicDataList[position]
            viewHolder.bindItem(itemMusic)
        }
    }

    fun updateList(newData: ArrayList<MusicDataModel>) {
        musicDataList.clear()
        musicDataList.addAll(newData)
        notifyDataSetChanged()

    }


    class MusicViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private lateinit var musicData: MusicDataModel
        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindItem(forecastData: MusicDataModel) {
            this.musicData = forecastData
        }
    }


}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

