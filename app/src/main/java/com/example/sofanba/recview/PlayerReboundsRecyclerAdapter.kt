package com.example.sofanba.recview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.databinding.ItemPlayerstatsBinding
import com.example.sofanba.model.Stats

class PlayerReboundsRecyclerAdapter: RecyclerView.Adapter<PlayerReboundsRecyclerAdapter.PlayerReboundsViewHolder>() {
    private var  stats = ArrayList<Stats>()
    fun setStats(stats: ArrayList<Stats>) {
        this.stats.clear()
        this.stats.addAll(stats)
        this.notifyDataSetChanged()
    }
    class PlayerReboundsViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        fun bind(stats: Stats) {

            val binding = ItemPlayerstatsBinding.bind(itemView)
            binding.tvPlayerstatsName.text=stats.player?.firstName +" "+ stats.player?.lastName
            binding.tvPlayerstatsTeam.text=stats.team?.abbreviation.toString()
            binding.tvStatvalue.text=stats.reb.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerReboundsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playerstats, parent, false)

        return PlayerReboundsViewHolder(view)
    }



    override fun getItemCount(): Int {
        return stats.count()
    }

    override fun onBindViewHolder(holder: PlayerReboundsViewHolder, position: Int) {
        val stat=stats[position]
        if (stat != null) {
            holder.bind(stat)
        }
    }


}