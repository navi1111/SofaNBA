package com.example.sofanba.util.main

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sofanba.model.Game
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import com.example.sofanba.network.paging.PlayerPagingSource
import com.example.sofanba.network.paging.TeamGamesPagingSource
import kotlinx.coroutines.launch

class PageViewModel : ViewModel() {
    val gamesList:MutableLiveData<List<Game>> = MutableLiveData<List<Game>>()
    var teamId:Int?=null
    /*fun getGamesofOneTeam(team: Team){
        viewModelScope.launch {
            gamesList.value=Network().getservice().getGamesbyTeam(team.id!!).games
        }
    }*/
    val flow = Pager(PagingConfig(pageSize = 20)){
        TeamGamesPagingSource(Network().getservice(),teamId!!)
    }.flow.cachedIn(viewModelScope)
    fun getTeamId(id:Int){
        teamId=id
    }
}