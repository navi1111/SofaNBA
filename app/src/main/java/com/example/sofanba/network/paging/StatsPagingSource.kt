package com.example.sofanba.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.model.Stats
import com.example.sofanba.network.Network
import com.example.sofanba.network.NetworkService
import com.example.sofanba.network.SofaNetwork

class StatsPagingSource(val service: NetworkService, var id:Array<Int>): PagingSource<Int, Stats>() {
    override fun getRefreshKey(state: PagingState<Int, Stats>): Int? {
        return state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Stats> {
        return try{
            val nextPageNumber=params.key ?: 0
            val response=service.getGameStatsforPlayer(nextPageNumber, id)
            val stats=getGamefromStats(response.stats)
            PagingSource.LoadResult.Page(

                data = stats,
                prevKey = null,
                nextKey = response.meta?.nextPage?.toInt()
            )
        } catch (e:Exception){
            PagingSource.LoadResult.Error(e)
        }
    }
    private suspend fun getGamefromStats(stats: ArrayList<Stats>) :ArrayList<Stats>{

        stats.forEach { stat ->
            stat.gameInfo= Network().getservice().getGamefromStats(stat.game?.id!!)


        }
        return stats
    }

}