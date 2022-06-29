package com.tapmobile.youtube.data

import com.google.api.services.youtube.model.ChannelListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("youtube/v3/search")
    fun getYouTubeVideos(
        @Query("key") apiKey: String?,
        @Query("channelId") channelId: String?,
        @Query("part") videoPart: String?,
        @Query("order") videoOrder: String?,
        @Query("maxResults") maxResults: Int
    ): Call<ChannelListResponse>
}