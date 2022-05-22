package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemTeamBinding
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper

class TeamRecyclerAdapter(val onTeamEventListener: OnTeamEventListener):RecyclerView.Adapter<TeamRecyclerAdapter.TeamViewHolder>() {
    private var  teams = ArrayList<Team>()


    fun setTeams(cities: ArrayList<Team>) {
        this.teams.clear()
        this.teams.addAll(cities)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team, parent, false)

        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
        onTeamEventListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onTeamSelected(team)
            }
        }


    }

    override fun getItemCount(): Int = teams.count()

    fun moveItem(from: Int, to: Int) {
        val fromEmoji = teams[from]
        teams.removeAt(from)
        if (to < from) {
            teams.add(to, fromEmoji)
        } else {
            teams.add(to - 1, fromEmoji)
        }
    }



    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            val teamIconHelper=TeamIconHelper()
            val binding = ItemTeamBinding.bind(itemView)
            binding.tvTeamName.text=team.fullName
            binding.ivTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(team.abbreviation!!))
            binding.imgbtnFavourite.isActivated=team.isFavourite
            binding.imgbtnFavourite.setOnClickListener {

                team.isFavourite=!team.isFavourite
                binding.imgbtnFavourite.isActivated=team.isFavourite
                onTeamEventListener.onTeamImageButtonSelected(team)

            }


        }

    }
}