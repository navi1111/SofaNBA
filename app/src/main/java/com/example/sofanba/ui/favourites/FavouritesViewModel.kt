package com.example.sofanba.ui.favourites

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.database.AppDatabase
import com.example.sofanba.model.FavouritePlayer
import com.example.sofanba.model.FavouriteTeam
import com.example.sofanba.model.Player
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel() {
    val favouritesList=MutableLiveData<List<Team>>()
    val favouritePlayerList=MutableLiveData<List<Player>>()
    fun getFavouriteTeams(context: Context){
        viewModelScope.launch {
            val startList= AppDatabase.getDatabase(context).teamDao().getAll()
            val asynctasks=startList.map {
                async {
                    Network().getservice().getSpecificTeam(it.id!!)

                }
            }
            val responses = asynctasks.awaitAll()
            favouritesList.value=responses as List<Team>

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
    fun getFavouritePlayers(context: Context){
        viewModelScope.launch {
            val startList= AppDatabase.getDatabase(context).playerDao().getAll()
            val asynctasks=startList.map {

                Network().getservice().getSpecificPlayer(it.id!!)
            }
            val responses = asynctasks
            favouritePlayerList.value=responses as List<Player>

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