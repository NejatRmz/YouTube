package com.tapmobile.youtube.domain.usecase

import com.google.api.services.youtube.model.ChannelListResponse
import com.tapmobile.youtube.common.Resource
import com.tapmobile.youtube.domain.repository.YouTubeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class YouTubeUseCase @Inject constructor(
    private val repository: YouTubeRepository
) {

    operator fun invoke(key: String, channelId: String, part: String, order: String, max: Int): Flow<Resource<List<ChannelListResponse>>> = flow {
        try {
            emit(Resource.Loading())
            //val result = repository.getYouTubeVideos(key, channelId, part, order, max)
            //emit(Resource.Success(result))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}