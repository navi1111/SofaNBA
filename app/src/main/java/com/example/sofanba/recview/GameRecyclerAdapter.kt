package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemGameBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper

class GameRecyclerAdapter(/*val onTeamEventListener: OnTeamEventListener*/): RecyclerView.Adapter<GameRecyclerAdapter.GameViewHolder>() {
    private var  games = ArrayList<Game>()


    fun setTeams(games: ArrayList<Game>) {
        this.games.clear()
        this.games.addAll(games)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)

        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        /*onTeamEventListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onTeamSelected(game)
            }
        }*/


    }

    override fun getItemCount(): Int = games.count()

    fun moveItem(from: Int, to: Int) {
        val fromEmoji = games[from]
        games.removeAt(from)
        if (to < from) {
            games.add(to, fromEmoji)
        } else {
            games.add(to - 1, fromEmoji)
        }
    }



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
}