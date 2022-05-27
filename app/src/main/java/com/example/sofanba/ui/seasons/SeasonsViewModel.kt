package com.example.sofanba.ui.seasons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sofanba.model.Game
import com.example.sofanba.network.Network
import com.example.sofanba.network.paging.GamePagingSource

class SeasonsViewModel : ViewModel() {

    val gameList=MutableLiveData<ArrayList<Game>>()
    /*fun getGames(){
        viewModelScope.launch {
            gameList.value= Network().getservice().getAllGames().games
        }
    }*/
    val flow = Pager(PagingConfig(pageSize = 20)){
        GamePagingSource(Network().getservice())
    }.flow.cachedIn(viewModelScope)
}