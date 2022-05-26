package com.example.sofanba.util.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import kotlinx.coroutines.launch

class TeamInfoViewModel : ViewModel(){
     val teamList: MutableLiveData<List<Team>> = MutableLiveData<List<Team>>()
    private var helperList:MutableList<Team> = mutableListOf()
    fun getTeams(team: Team){
        viewModelScope.launch {
            Network().getservice().getAllTeams().teams.map {
                if(it.division==team.division){
                    helperList.add(it)
                }
            }
            teamList.value=helperList
        }
    }
}