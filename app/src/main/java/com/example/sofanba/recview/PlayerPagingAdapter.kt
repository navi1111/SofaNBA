package com.example.sofanba.recview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemPlayerBinding
import com.example.sofanba.model.Player
import com.example.sofanba.model.PlayersData

class PlayerPagingAdapter(private val context: Context, diffCallback:DiffUtil.ItemCallback<Player>):PagingDataAdapter<Player,PlayerPagingAdapter.PlayerViewHolder>(diffCallback) {
    val onPlayerEventListener:OnPlayerEventListener?=null
    inner class PlayerViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun bind(player: Player) {

            val binding = ItemPlayerBinding.bind(itemView)
            binding.tvPlayerName.text=player.firstName +" "+ player.lastName
            binding.tvPlayerTeam.text=player.team?.abbreviation.toString()
            binding.btnFavourite.isActivated=player.isFavourite
            binding.btnFavourite.setOnClickListener {

                player.isFavourite=!player.isFavourite
                binding.btnFavourite.isActivated=player.isFavourite
                onPlayerEventListener?.onImageButtonSelected(player)

            }


        }
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player=getItem(position)
        if (player != null) {
            holder.bind(player)
        }
        onPlayerEventListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onPlayerSelected(player!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)

        return PlayerViewHolder(view)
    }
}