package com.example.sofanba.ui.match.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sofanba.model.Game
import com.example.sofanba.model.Stats
import com.example.sofanba.network.Network
import kotlinx.coroutines.launch

class MatchLeadersViewModel : ViewModel() {
    private val _stats= MutableLiveData<ArrayList<Stats>>()
    val stats: LiveData<ArrayList<Stats>> get() = _stats
    fun getStatsforGame(game: Game){
        viewModelScope.launch {
            _stats.value= Network().getservice().getGameStats(arrayOf(game.id!!)).stats
        }
    }
    fun findTopKFGPct(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {
            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].fgPct!! > array[maxIndex].fgPct!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }
            return topKList
        }
    }
    fun findTopKThreePct(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {
            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].fg3Pct!! > array[maxIndex].fg3Pct!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }
            return topKList
        }
    }
    fun findTopKAst(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {
            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].ast!! > array[maxIndex].ast!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }
            return topKList
        }
    }
    fun findTopKReb(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {
            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].reb!! > array[maxIndex].reb!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }
            return topKList
        }
    }
    fun findTopKOffReb(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {
            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].oreb!! > array[maxIndex].oreb!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }
            return topKList
        }
    }
    fun findTopKTov(input: ArrayList<Stats>?, k: Int): List<Stats>? {
        if (input== emptyList<Stats>()){
            return input
        }else {


            //val array: ArrayList<Stats> = ArrayList(input)
            val array: ArrayList<Stats> = input!!
            val topKList: MutableList<Stats> = ArrayList()
            for (i in 0 until k) {
                var maxIndex = 0
                for (j in 1 until array.size) {
                    if (array[j].turnover!! > array[maxIndex].turnover!!) {
                        maxIndex = j
                    }
                }
                topKList.add(array.elementAt(maxIndex))
                array.removeAt(maxIndex)
            }

            return topKList
        }
    }
}