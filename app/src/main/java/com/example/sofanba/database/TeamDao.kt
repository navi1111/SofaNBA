package com.example.sofanba.database

import androidx.room.*
import com.example.sofanba.model.FavouriteTeam

@Dao
interface TeamDao {
    @Query("SELECT * FROM FavouriteTeam")
    suspend fun getAll(): List<FavouriteTeam>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteTeam(team: FavouriteTeam)



    @Delete
    suspend fun deleteTeam(team: FavouriteTeam)


    @Query("DELETE  FROM FavouriteTeam")
    suspend fun clearFavouriteTeams()
}