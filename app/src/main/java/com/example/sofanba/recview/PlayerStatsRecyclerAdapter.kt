package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemPlayerBinding
import com.example.sofanba.databinding.ItemPlayerstatsBinding
import com.example.sofanba.model.Player
import com.example.sofanba.model.Stats

class PlayerStatsRecyclerAdapter: RecyclerView.Adapter<PlayerStatsRecyclerAdapter.PlayerStatsViewHolder>()  {
    private var  stats = ArrayList<Stats>()
    fun setStats(stats: ArrayList<Stats>) {
        this.stats.clear()
        this.stats.addAll(stats)
        this.notifyDataSetChanged()
    }
    class PlayerStatsViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(stats: Stats) {

            val binding = ItemPlayerstatsBinding.bind(itemView)
            binding.tvPlayerstatsName.text=stats.player?.firstName +" "+ stats.player?.lastName
            binding.tvPlayerstatsTeam.text=stats.team?.abbreviation.toString()
            binding.tvStatvalue.text=stats.fgm.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerStatsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playerstats, parent, false)

        return PlayerStatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerStatsViewHolder, position: Int) {
        val stat=stats[position]
        if (stat != null) {
            holder.bind(stat)
        }

    }

    override fun getItemCount(): Int {
        return stats.count()
    }
}