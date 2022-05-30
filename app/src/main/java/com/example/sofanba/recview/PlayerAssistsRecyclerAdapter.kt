package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemPlayerstatsBinding
import com.example.sofanba.model.Stats

class PlayerAssistsRecyclerAdapter: RecyclerView.Adapter<PlayerAssistsRecyclerAdapter.PlayerAssistsViewHolder>() {
    private var  stats = ArrayList<Stats>()
    fun setStats(stats: ArrayList<Stats>) {
        this.stats.clear()
        this.stats.addAll(stats)
        this.notifyDataSetChanged()
    }
    class PlayerAssistsViewHolder (itemview: View): RecyclerView.ViewHolder(itemview){
        fun bind(stats: Stats) {

            val binding = ItemPlayerstatsBinding.bind(itemView)
            binding.tvPlayerstatsName.text=stats.player?.firstName +" "+ stats.player?.lastName
            binding.tvPlayerstatsTeam.text=stats.team?.abbreviation.toString()
            binding.tvStatvalue.text=stats.ast.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAssistsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playerstats, parent, false)

        return PlayerAssistsRecyclerAdapter.PlayerAssistsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerAssistsViewHolder, position: Int) {
        val stat=stats[position]
        if (stat != null) {
            holder.bind(stat)
        }
    }

    override fun getItemCount(): Int {
        return stats.count()
    }
}