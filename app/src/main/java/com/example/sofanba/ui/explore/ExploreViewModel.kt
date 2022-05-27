package com.example.sofanba.ui.explore


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sofanba.database.AppDatabase
import com.example.sofanba.model.FavouritePlayer
import com.example.sofanba.model.FavouriteTeam
import com.example.sofanba.model.Player
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import com.example.sofanba.network.paging.PlayerPagingSource
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {
    val teamList=MutableLiveData<ArrayList<Team>>()
    val flow = Pager(PagingConfig(pageSize = 20)){
        PlayerPagingSource(Network().getservice())
    }.flow.cachedIn(viewModelScope)

    fun getTeams(){
        viewModelScope.launch {
            teamList.value=Network().getservice().getAllTeams().teams
        }
    }
    fun insertFavouriteTeam(context: Context, team: Team){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).teamDao().insertFavouriteTeam(FavouriteTeam( team.id!!))
        }

    }
    fun deleteFavouriteTeam(context: Context, team: Team){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).teamDao().deleteTeam(FavouriteTeam(team.id!!))
        }
    }
    fun insertFavouritePlayer(context: Context, player: Player){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).playerDao().insertFavouritePlayer(FavouritePlayer( player.id!!))
        }

    }
    fun deleteFavouritePlayer(context: Context, player: Player){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).playerDao().deletePlayer(FavouritePlayer(player.id!!))
        }
    }
}