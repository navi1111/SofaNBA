package com.example.sofanba.network


import com.example.sofanba.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("players")
    suspend fun getAllPlayers(@Query ("page") page: Int):PlayersData

    @GET("teams")
    suspend fun getAllTeams():TeamsData

    @GET("teams/{ID}")
    suspend fun getSpecificTeam(@Path("ID")id:Int):Team

    @GET("games")
    suspend fun getAllGames(@Query ("page" ) page: Int):GamesData

    @GET ("games")
    suspend fun getGamesbyTeam(@Query("team_ids") teamIds:Int):GamesData

    @GET("players/{ID}")
    suspend fun getSpecificPlayer(@Path("ID")id:Int): Player




}