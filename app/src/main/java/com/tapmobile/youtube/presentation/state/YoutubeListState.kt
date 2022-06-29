package com.tapmobile.youtube.presentation.state

import com.google.api.services.youtube.model.ChannelListResponse

data class YoutubeListState(
    val isLoading: Boolean = false,
    val responses: List<ChannelListResponse> = emptyList(),
    val error: String = ""
)
