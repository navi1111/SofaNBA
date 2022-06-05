package com.example.sofanba.network.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sofanba.model.Player
import com.example.sofanba.model.PlayersData
import com.example.sofanba.network.NetworkService
import com.example.sofanba.network.SofaNetwork
import kotlinx.coroutines.launch

class PlayerPagingSource(val service: NetworkService): PagingSource<Int, Player>() {
    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try{
            val nextPageNumber=params.key ?: 0
            val response=service.getAllPlayers(nextPageNumber)
            val players=getPlayerImages(response.players)
            LoadResult.Page(
                data = players,
                prevKey = null,
                nextKey = response.meta?.nextPage?.toInt()
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    private suspend fun getPlayerImages(players: ArrayList<Player>) :ArrayList<Player>{
            var playersWithoutImage:ArrayList<Player> = players
       /* try {
            players.forEach { player ->

                player.imageUrl= player.id?.let { SofaNetwork().getservice().getPlayerImage(it).imageUrl }


            }
            return players
        }catch (e:Exception){
            return playersWithoutImage
        }*/
            players.forEach { player ->
                try {
                    player.imageUrl= SofaNetwork().getservice().getPlayerImage(player.id!!).data[0].imageUrl
                }catch (e:Exception){
                    player.imageUrl=null
                }

                    //player.imageUrl= player.id?.let { SofaNetwork().getservice().getPlayerImage(it).imageUrl }


        }
        return players
    }
}