package com.example.sofanba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sofanba.model.FavouritePlayer
import com.example.sofanba.model.FavouriteTeam

@Database(entities = [FavouritePlayer::class, FavouriteTeam::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun playerDao():PlayerDao
    abstract fun teamDao(): TeamDao
    companion object{
        private var instance:AppDatabase?=null
        fun getDatabase(context: Context): AppDatabase {
            if(instance==null){
                instance=buildDatabase(context)
            }
            return instance!!
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context,
            AppDatabase::class.java, "NBAdatabase"
        ).fallbackToDestructiveMigration().build()


    }
}