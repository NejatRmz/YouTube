package com.tapmobile.youtube.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapmobile.youtube.common.Resource
import com.tapmobile.youtube.domain.usecase.YouTubeUseCase
import com.tapmobile.youtube.presentation.state.YoutubeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class YouTubeListViewModel @Inject constructor(
    private val youTubeUseCase: YouTubeUseCase,
):ViewModel() {

    private val _state = MutableStateFlow<YoutubeListState>(YoutubeListState())
    val state: StateFlow<YoutubeListState> = _state


    init {
        getYouTubeVideos("usd", "", "", "", 1)
    }

    fun getYouTubeVideos(key: String, channelId: String, part: String, order: String, max: Int){
        youTubeUseCase(key, channelId, part, order, max).onEach { result->
            when(result){
                is Resource.Success -> {
                    _state.value = YoutubeListState(responses = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = YoutubeListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value= YoutubeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        private const val TAG = "YoutubeList"
    }

}