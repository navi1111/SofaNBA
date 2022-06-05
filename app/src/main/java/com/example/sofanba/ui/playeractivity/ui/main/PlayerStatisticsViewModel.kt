package com.example.sofanba.ui.playeractivity.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.model.SeasonAverages
import com.example.sofanba.network.Network
import kotlinx.coroutines.launch

class PlayerStatisticsViewModel:ViewModel() {
    val seasonAverages2021: MutableLiveData<ArrayList<SeasonAverages>> = MutableLiveData<ArrayList<SeasonAverages>>()
    val seasonAverages2020: MutableLiveData<ArrayList<SeasonAverages>> = MutableLiveData<ArrayList<SeasonAverages>>()
    val seasonAverages2019: MutableLiveData<ArrayList<SeasonAverages>> = MutableLiveData<ArrayList<SeasonAverages>>()
    val seasonAverages2018: MutableLiveData<ArrayList<SeasonAverages>> = MutableLiveData<ArrayList<SeasonAverages>>()
    fun getSeasonAveragefor2021(player: Player){
        viewModelScope.launch {
            seasonAverages2021.value=Network().getservice().getSeasonAverages(arrayOf(player.id?:0),2021).data
        }

    }
    fun getSeasonAveragefor2020(player: Player){
        viewModelScope.launch {
            seasonAverages2020.value=Network().getservice().getSeasonAverages(arrayOf(player.id?:0),2020).data
        }

    }
    fun getSeasonAveragefor2019(player: Player){
        viewModelScope.launch {
            seasonAverages2019.value=Network().getservice().getSeasonAverages(arrayOf(player.id?:0),2019).data
        }

    }
    fun getSeasonAveragefor2018(player: Player){
        viewModelScope.launch {
            seasonAverages2018.value=Network().getservice().getSeasonAverages(arrayOf(player.id?:0),2018).data
        }

    }
}