package com.example.sofanba.network


import com.example.sofanba.model.GamesData
import com.example.sofanba.model.PlayersData
import com.example.sofanba.model.Team
import com.example.sofanba.model.TeamsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("players")
    suspend fun getAllPlayers(page:Int):PlayersData

    @GET("teams")
    suspend fun getAllTeams():TeamsData

    @GET("teams/{ID}")
    suspend fun getSpecificTeam(@Path("ID")id:Int):Team

    @GET("games")
    suspend fun getAllGames(page: Int):GamesData


}