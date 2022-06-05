package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemGameBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Stats
import com.example.sofanba.util.TeamIconHelper

class StatsPagingAdapter(val onGameEventListener: OnGameEventListener, diffCallback: DiffUtil.ItemCallback<Stats>): PagingDataAdapter<Stats, StatsPagingAdapter.StatsViewHolder>(diffCallback) {
    class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stats: Stats) {
            val teamIconHelper= TeamIconHelper()
            val binding = ItemGameBinding.bind(itemView)
            binding.firstTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(stats.gameInfo?.homeTeam?.abbreviation ?: "LAL"))
            binding.secondTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(stats.gameInfo?.visitorTeam?.abbreviation ?: "BOS"))
            binding.tvFirstScore.text=stats.game?.homeTeamScore.toString()
            binding.tvSecondScore.text=stats.game?.visitorTeamScore.toString()
            binding.tvGametime.text=stats.gameInfo?.status
            binding.tvDate.text=stats.gameInfo?.date
            binding.tvFirstTeamAbb.text=stats.gameInfo?.homeTeam?.abbreviation
            binding.tvSecondTeamAbb.text=stats.gameInfo?.visitorTeam?.abbreviation


        }
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val stat = getItem(position)
        holder.bind(stat!!)
        onGameEventListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onGameSelected(stat?.gameInfo!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)

        return StatsViewHolder(view)
    }
}