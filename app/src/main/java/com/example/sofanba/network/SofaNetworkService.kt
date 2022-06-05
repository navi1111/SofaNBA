package com.example.sofanba.network

import com.example.sofanba.model.PlayerImage
import com.example.sofanba.model.PlayerImageData
import com.example.sofanba.model.YTVideo
import com.example.sofanba.model.YTVideoData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SofaNetworkService {
    @GET("/api/v1/academy/player-image/player/{playerId}")
    suspend fun getPlayerImage(@Path("playerId") id:Int):PlayerImageData

    @POST("/api/v1/academy/player-image")
    suspend fun addPlayerImage(@Body playerImage: PlayerImage)

    @POST("/api/v1/academy/highlight")
    suspend fun addYtVideo(@Body ytVideo: YTVideo)

    @GET("/api/v1/academy/highlight/event/{eventId}")
    suspend fun getYtVideo(@Path("eventId") id: Int):YTVideoData
}