package com.tapmobile.youtube.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.api.services.youtube.model.ChannelListResponse
import com.tapmobile.youtube.databinding.YoutubeListItemBinding

class YouTubeListAdapter(private val youtubeList: ArrayList<ChannelListResponse>) :
    RecyclerView.Adapter<YouTubeListAdapter.YoutubeListHolder>(){

    private lateinit var binding: YoutubeListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeListHolder {
        binding = YoutubeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YoutubeListHolder(binding)
    }

    override fun onBindViewHolder(holder: YoutubeListHolder, position: Int) {
        val youtube: ChannelListResponse = youtubeList[position]
        holder.bind(youtube)
        holder.itemView.setOnClickListener {
            Log.d(TAG, youtube.eventId)
        }

    }

    override fun getItemCount(): Int {
        return youtubeList.size
    }

    fun updateYoutubeDataList(newList: List<ChannelListResponse>) {
        youtubeList.clear()
        youtubeList.addAll(newList)
        notifyDataSetChanged()
    }

    class YoutubeListHolder(val itemBinding: YoutubeListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(youtube: ChannelListResponse) {
            youtube.apply {
                Log.d(TAG, youtube.eventId)


            }
        }
    }

    companion object {
        private const val TAG = "YoutubeListAdapter"
    }

}
