package com.example.sofanba.ui.playeractivity.ui.main

import androidx.lifecycle.*
import com.example.sofanba.model.PlayerImage
import com.example.sofanba.network.SofaNetwork
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    fun setPlayerImage(playerImage: PlayerImage){
        viewModelScope.launch {
            SofaNetwork().getservice().addPlayerImage(playerImage)
        }
    }
}