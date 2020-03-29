package com.ysp.camep.ui.videoplaylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.VideoPlaylistItemBinding

class VideoPlaylistAdapter(private val listener: (playlist: Playlist) -> Unit) :
    RecyclerView.Adapter<VideoPlaylistAdapter.VideoPlaylistItemViewHolder>() {
    private var items: List<Playlist> = listOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoPlaylistItemViewHolder {
        return VideoPlaylistItemViewHolder(
            VideoPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: VideoPlaylistItemViewHolder, position: Int
    ) {
        holder.backgroundView.setOnClickListener { listener(items[position]) }
        holder.name.text = items[position].name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Playlist>) {
        YLog.methodIn(items.toString())
        this.items = items
        notifyDataSetChanged()
    }

    class VideoPlaylistItemViewHolder(binding: VideoPlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val backgroundView: View = binding.backgroundView
        val name: TextView = binding.name
    }
}