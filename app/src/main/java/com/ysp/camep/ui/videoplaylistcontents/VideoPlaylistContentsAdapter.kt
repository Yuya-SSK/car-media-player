package com.ysp.camep.ui.videoplaylistcontents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.databinding.VideoPlaylistContentsItemBinding
import com.ysp.camep.ui.videoplaylistcontents.VideoPlaylistContentsAdapter.VideoPlaylistContentsItemViewHolder

class VideoPlaylistContentsAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<VideoPlaylistContentsItemViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(videoContent: VideoContent)
    }

    private var items: List<VideoContent> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoPlaylistContentsItemViewHolder {
        return VideoPlaylistContentsItemViewHolder(
            VideoPlaylistContentsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: VideoPlaylistContentsItemViewHolder, position: Int) {
        holder.backgroundView.setOnClickListener { listener.onItemClick(items[position]) }
        holder.thumbnailView.setImageBitmap(items[position].thumbnail)
        holder.titleView.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<VideoContent>) {
        this.items = items
        notifyDataSetChanged()
    }

    class VideoPlaylistContentsItemViewHolder(binding: VideoPlaylistContentsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val backgroundView: View = binding.backgroundView
        val thumbnailView: ImageView = binding.thumbnailView
        val titleView: TextView = binding.titleView
    }
}