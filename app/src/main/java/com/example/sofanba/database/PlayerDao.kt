package com.example.sofanba.database

import androidx.room.*
import com.example.sofanba.model.FavouritePlayer

@Dao
interface PlayerDao {
    @Query("SELECT * FROM FavouritePlayer")
    suspend fun getAll(): List<FavouritePlayer>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritePlayer(player: FavouritePlayer)




    @Delete
    suspend fun deletePlayer(player: FavouritePlayer)


    @Query("DELETE  FROM FavouritePlayer")
    suspend fun clearFavouritePlayers()
}