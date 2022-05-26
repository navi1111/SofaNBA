package com.example.sofanba.util.main

import androidx.lifecycle.*
import com.example.sofanba.model.Game
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import kotlinx.coroutines.launch

class PageViewModel : ViewModel() {
    val gamesList:MutableLiveData<List<Game>> = MutableLiveData<List<Game>>()
    fun getGamesofOneTeam(team: Team){
        viewModelScope.launch {
            gamesList.value=Network().getservice().getGamesbyTeam(team.id!!).games
        }
    }
}