package com.example.sofanba.recview

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemGameBinding
import com.example.sofanba.databinding.ItemSimpleGameBinding
import com.example.sofanba.model.Game
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class GameRecyclerAdapter(val team:Team/*val onTeamEventListener: OnTeamEventListener*/): RecyclerView.Adapter<GameRecyclerAdapter.SimpleGameViewHolder>() {
    private var  games = ArrayList<Game>()


    fun setTeams(games: ArrayList<Game>) {
        this.games.clear()
        this.games.addAll(games)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleGameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple_game, parent, false)

        return SimpleGameViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleGameViewHolder, position: Int) {
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



    inner class SimpleGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game){

            val teamIconHelper= TeamIconHelper()
            val binding = ItemSimpleGameBinding.bind(itemView)
            binding.tvGamedate.text=game.date.toString()
            binding.teamFirstScore.text=game?.homeTeamScore.toString()
            binding.teamSecondScore.text=game?.visitorTeamScore.toString()
            if(team.id==game.homeTeam?.id){
                binding.tvGameplace.text="vs"
                binding.ivOpponentPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.visitorTeam?.abbreviation!!))
                binding.tvOpponentAbb.text=game?.visitorTeam?.abbreviation


                if (game.homeTeamScore?.compareTo(game.visitorTeamScore!!)!! > 0){
                    binding.tvGameOutcome.text="W"
                }else{
                    binding.tvGameOutcome.text="L"
                }

            }
            if(team.id==game.visitorTeam?.id){
                binding.tvGameplace.text="@"
                binding.ivOpponentPhoto.setImageResource(teamIconHelper.getWeatherIcon(game.homeTeam?.abbreviation!!))

                if (game.visitorTeamScore?.compareTo(game.homeTeamScore!!)!! > 0){
                    binding.tvGameOutcome.text="W"
                }else{
                    binding.tvGameOutcome.text="L"
                }
            }







        }

    }
}