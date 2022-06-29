package com.tapmobile.youtube.data

import com.google.api.services.youtube.model.ChannelListResponse
import com.tapmobile.youtube.domain.repository.YouTubeRepository
import retrofit2.Call
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private val youTubeApi: YouTubeApi
) : YouTubeRepository {

    override suspend fun getYouTubeVideos(key: String, channelId: String, part: String, order: String, max: Int): Call<ChannelListResponse> {
        return youTubeApi.getYouTubeVideos(key, channelId, part, order, max)
    }
}