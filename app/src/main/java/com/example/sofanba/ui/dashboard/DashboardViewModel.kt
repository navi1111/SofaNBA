package com.example.sofanba.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.database.AppDatabase
import com.example.sofanba.model.FavouriteTeam
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    val favouritesList=MutableLiveData<List<Team>>()
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

}