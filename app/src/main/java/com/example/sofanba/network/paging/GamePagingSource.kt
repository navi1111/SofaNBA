package com.example.sofanba.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sofanba.model.Game
import com.example.sofanba.model.Player
import com.example.sofanba.network.NetworkService

class GamePagingSource(val service: NetworkService): PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try{
            val nextPageNumber=params.key ?: 0
            val response=service.getAllGames(nextPageNumber+20)
            LoadResult.Page(
                data = response.games,
                prevKey = null,
                nextKey = response.meta?.nextPage?.toInt()
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}