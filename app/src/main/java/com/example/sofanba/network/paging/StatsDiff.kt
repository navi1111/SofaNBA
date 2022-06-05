package com.example.sofanba.network.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.sofanba.model.Stats

object StatsDiff: DiffUtil.ItemCallback<Stats>() {
    override fun areItemsTheSame(oldItem: Stats, newItem: Stats): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Stats, newItem: Stats): Boolean {
        return oldItem==newItem
    }
}