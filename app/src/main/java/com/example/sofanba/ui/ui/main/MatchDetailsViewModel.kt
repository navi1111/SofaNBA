package com.example.sofanba.ui.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.model.Game
import com.example.sofanba.model.Stats
import com.example.sofanba.model.Team
import com.example.sofanba.network.Network
import kotlinx.coroutines.launch

class MatchDetailsViewModel:ViewModel() {
    private val _stats= MutableLiveData<ArrayList<Stats>>()
    val stats:LiveData<ArrayList<Stats>> get() = _stats
    fun getStatsforGame(game: Game){
        viewModelScope.launch {
            _stats.value= Network().getservice().getGameStats(arrayOf(game.id!!)).stats
        }
    }
    fun calculateTotalFgPct(statsArrayList: ArrayList<Stats>, team: Team):Double{
        var fgpct:Double=0.0
        var count:Double=0.0
        var tempArrayList :  ArrayList<Stats> = ArrayList<Stats>()
        tempArrayList=_stats.value?.filter {
            it.team?.abbreviation==team.abbreviation
        } as ArrayList<Stats>
        tempArrayList.forEach{
            fgpct=fgpct+it.fgPct!!
        }
        return fgpct/tempArrayList.size

    }
    fun calculateTotalThreePtPct(statsArrayList: ArrayList<Stats>, team: Team):Double{
        var i:Int=0
        var fgpct:Double= 0.0
        for(stat in statsArrayList){
            if(stat.team?.id==team.id){
                fgpct += stat.fg3Pct!!.toDouble()
                i++
            }

        }
        fgpct=fgpct/i
        return fgpct
    }
    fun calculateTotalAssists( team: Team):Double{
        var i:Int=0
        var fgpct:Double= 0.0

        _stats.value?.forEach {


            it.team?.abbreviation==team.abbreviation
            fgpct+=it.ast!!

        }



        return fgpct
    }
    fun calculateTotalRebounds(statsArrayList: ArrayList<Stats>, team: Team):Int{

        var fgpct:Int= 0
        for(stat in statsArrayList){
            if(stat.team?.id==team.id){
                fgpct += stat.reb!!

            }

        }
        return fgpct
    }
    fun calculateTotalTurnovers(statsArrayList: ArrayList<Stats>, team: Team):Int{
        var i:Int=0
        var fgpct:Int= 0
        for(stat in statsArrayList){
            if(stat.team?.id==team.id){
                fgpct += stat.turnover!!
            }

        }
        return fgpct
    }
    fun calculateTotalOffensiveRebounds(statsArrayList: ArrayList<Stats>, team: Team):Int{
        var i:Int=0
        var fgpct:Int= 0
        for(stat in statsArrayList){
            if(stat.team?.id==team.id){
                fgpct += stat.oreb!!
            }
        }
        return fgpct
    }

}