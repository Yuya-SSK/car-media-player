package com.ysp.camep.ui.videocontents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.databinding.VideoRecyclerItemBinding
import com.ysp.camep.ui.videocontents.VideoContentsAdapter.VideoRecyclerViewHolder

class VideoContentsAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<VideoRecyclerViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(videoContent: VideoContent)
    }

    private var items: List<VideoContent> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoRecyclerViewHolder {
        return VideoRecyclerViewHolder(
            VideoRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: VideoRecyclerViewHolder, position: Int) {
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

    class VideoRecyclerViewHolder(binding: VideoRecyclerItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val backgroundView: View = binding.backgroundView
        val thumbnailView: ImageView = binding.thumbnailView
        val titleView: TextView = binding.titleView
    }
}