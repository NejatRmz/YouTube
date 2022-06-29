package com.tapmobile.youtube.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.services.youtube.model.ChannelListResponse
import com.tapmobile.youtube.common.Constants
import com.tapmobile.youtube.databinding.FragmentYoutubeListBinding
import com.tapmobile.youtube.presentation.adapter.YouTubeListAdapter
import com.tapmobile.youtube.presentation.viewmodel.YouTubeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YouTubeListFragment : Fragment() {

    private lateinit var binding: FragmentYoutubeListBinding
    private lateinit var youtubeListAdapter: YouTubeListAdapter
    private val viewModel: YouTubeListViewModel by viewModels()

    private var time = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        youtubeListAdapter = YouTubeListAdapter(arrayListOf())
        binding = FragmentYoutubeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(binding.root.context)
        binding.rvYoutubeList.layoutManager = layoutManager
        binding.rvYoutubeList.adapter=youtubeListAdapter

        binding.btnSearch.setOnClickListener {
            getYouTubeVideos()
        }
    }



    private fun fetchData(){
        lifecycleScope.launch {
            viewModel.getYouTubeVideos(
                Constants.API_KEY,
                "UCjXfkj5iapKHJrhYfAF9ZGg", "snippet", "date", 20)
        }
    }

    private fun getYouTubeVideos(){
        lifecycleScope.launch {
            viewModel.state.collect() {
                Log.e(TAG, "time: ${time++}")
                when {
                    it.isLoading -> {
                        Log.d(TAG,"Loading")
                    }
                    it.error.isNotBlank() -> {
                        Log.d(TAG,"Error")
                    }
                    it.responses.isNotEmpty() ->{
                        Log.d(TAG,"Success")
                        var youtubeList = arrayListOf<ChannelListResponse>()
                        for (item in it.responses) {
                            youtubeList.add(item)
                        }
                        youtubeListAdapter.updateYoutubeDataList(youtubeList)
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "YoutubeListFragment"
    }

}