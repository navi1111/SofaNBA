package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemSimpleTeamBinding
import com.example.sofanba.databinding.ItemTeamBinding
import com.example.sofanba.model.Team
import com.example.sofanba.util.TeamIconHelper

class SimpleTeamAdapter(val onTeamEventListener: OnTeamEventListener):RecyclerView.Adapter<SimpleTeamAdapter.SimpleTeamViewHolder>() {
    private var  teams = ArrayList<Team>()


    fun setTeams(cities: ArrayList<Team>) {
        this.teams.clear()
        this.teams.addAll(cities)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleTeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple_team, parent, false)

        return SimpleTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleTeamViewHolder, position: Int) {
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



    inner class SimpleTeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            val teamIconHelper= TeamIconHelper()
            val binding = ItemSimpleTeamBinding.bind(itemView)
            binding.tvItemteamAbb.text=team.abbreviation
            binding.ivItemTeamPhoto.setImageResource(teamIconHelper.getWeatherIcon(team.abbreviation!!))




        }

    }
}