package com.example.sofanba.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.database.AppDatabase
import com.example.sofanba.model.FavouritePlayer
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    fun clearFavouritePlayerList(context: Context){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).playerDao().clearFavouritePlayers()
        }
    }
    fun clearFavouriteTeamList(context: Context){
        viewModelScope.launch {
            AppDatabase.getDatabase(context).teamDao().clearFavouriteTeams()
        }
    }


}