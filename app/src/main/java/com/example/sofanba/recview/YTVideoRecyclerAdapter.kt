package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemTeamBinding
import com.example.sofanba.databinding.ItemYoutubeBinding
import com.example.sofanba.model.Team
import com.example.sofanba.model.YTVideo

class YTVideoRecyclerAdapter() : RecyclerView.Adapter<YTVideoRecyclerAdapter.YTViewHolder>()  {
    private var  videos = ArrayList<YTVideo>()
    class YTViewHolder(itemview:View) :RecyclerView.ViewHolder(itemview){
        fun bind(video:YTVideo){
            val binding = ItemYoutubeBinding.bind(itemView)
            binding.ytVideoTitle.text=video.name
            Glide.with(itemView.context).load("http://img.youtube.com/vi/"+ video.url + "/mqdefault.jpg").placeholder(R.drawable.ic_player_placeholder_graphic_80_dp)
                .error(R.drawable.ic_player_placeholder_graphic_80_dp).into(binding.ytThumbnail)
        }

    }
    fun setVideos(videos: ArrayList<YTVideo>) {
        this.videos.clear()
        this.videos.addAll(videos)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YTViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_youtube, parent, false)

        return YTViewHolder(view)
    }

    override fun onBindViewHolder(holder: YTViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)

    }

    override fun getItemCount(): Int {
        return videos.count()
    }
}