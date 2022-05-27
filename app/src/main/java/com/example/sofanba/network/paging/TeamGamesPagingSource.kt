package com.example.sofanba.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sofanba.model.Game
import com.example.sofanba.network.NetworkService

class TeamGamesPagingSource (val service: NetworkService, var id:Int): PagingSource<Int, Game>(){
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Game> {
        return try{
            val nextPageNumber=params.key ?: 0
            val response=service.getGamesbyTeam(nextPageNumber, id)
            PagingSource.LoadResult.Page(
                data = response.games,
                prevKey = null,
                nextKey = response.meta?.nextPage?.toInt()
            )
        } catch (e:Exception){
            PagingSource.LoadResult.Error(e)
        }
    }
}