package com.example.sofanba.ui.playeractivity.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sofanba.network.Network
import com.example.sofanba.network.paging.GamePagingSource
import com.example.sofanba.network.paging.StatsPagingSource

class PlayerMatchesViewModel:ViewModel() {
    var playerId:Int?=null
    val flow = Pager(PagingConfig(pageSize = 20)){
        StatsPagingSource(Network().getservice(), arrayOf(playerId!!))
    }.flow.cachedIn(viewModelScope)
    fun getPlayerId(id:Int){
        playerId=id
    }
}