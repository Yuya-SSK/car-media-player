package com.ysp.camep.ui.videocontents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.databinding.VideoContentsItemBinding
import com.ysp.camep.ui.videocontents.VideoContentsAdapter.VideoContentsItemViewHolder

class VideoContentsAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<VideoContentsItemViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(videoContent: VideoContent)
    }

    private var items: List<VideoContent> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoContentsItemViewHolder {
        return VideoContentsItemViewHolder(
            VideoContentsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: VideoContentsItemViewHolder, position: Int) {
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

    class VideoContentsItemViewHolder(binding: VideoContentsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val backgroundView: View = binding.backgroundView
        val thumbnailView: ImageView = binding.thumbnailView
        val titleView: TextView = binding.titleView
    }
}