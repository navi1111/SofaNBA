package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemPlayerstatsBinding
import com.example.sofanba.model.Stats

class PlayerTurnoverRecyclerAdapter: RecyclerView.Adapter<PlayerTurnoverRecyclerAdapter.PlayerTurnoverViewHolder>() {
    private var  stats = ArrayList<Stats>()
    fun setStats(stats: ArrayList<Stats>) {
        this.stats.clear()
        this.stats.addAll(stats)
        this.notifyDataSetChanged()
    }
    class PlayerTurnoverViewHolder (itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(stats: Stats) {

            val binding = ItemPlayerstatsBinding.bind(itemView)
            binding.tvPlayerstatsName.text=stats.player?.firstName +" "+ stats.player?.lastName
            binding.tvPlayerstatsTeam.text=stats.team?.abbreviation.toString()
            binding.tvStatvalue.text=stats.turnover.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerTurnoverViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playerstats, parent, false)

        return PlayerTurnoverRecyclerAdapter.PlayerTurnoverViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerTurnoverViewHolder, position: Int) {
        val stat=stats[position]
        if (stat != null) {
            holder.bind(stat)
        }
    }

    override fun getItemCount(): Int {
        return stats.count()
    }
}