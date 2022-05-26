package com.example.sofanba.recview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemGameBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.util.TeamIconHelper

class GamePagingAdapter(private val context: Context, diffCallback: DiffUtil.ItemCallback<Game>): PagingDataAdapter<Game, GamePagingAdapter.GameViewHolder>(diffCallback) {


    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            val teamIconHelper= TeamIconHelper()
            val binding = ItemGameBinding.bind(itemView)
            binding.firstTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.homeTeam?.abbreviation ?: "LAL"))
            binding.secondTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.visitorTeam?.abbreviation ?: "BOS"))
            binding.tvFirstScore.text=game.homeTeamScore.toString()
            binding.tvSecondScore.text=game.visitorTeamScore.toString()
            binding.tvGametime.text=game.status
            binding.tvDate.text=game.date
            binding.tvFirstTeamAbb.text=game.homeTeam?.abbreviation
            binding.tvSecondTeamAbb.text=game.visitorTeam?.abbreviation


        }

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)

        return GameViewHolder(view)
    }

}