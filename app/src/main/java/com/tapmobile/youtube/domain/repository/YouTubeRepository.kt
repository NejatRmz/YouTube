package com.tapmobile.youtube.domain.repository
import com.google.api.services.youtube.model.ChannelListResponse
import retrofit2.Call

interface YouTubeRepository {

    suspend fun getYouTubeVideos(key: String, channelId: String, part: String, order: String, max: Int): Call<ChannelListResponse>


}