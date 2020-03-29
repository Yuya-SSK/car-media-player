package com.ysp.camep.ui.videoplaylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.VideoPlaylistFragmentBinding
import com.ysp.camep.ui.video.VideoFragmentDirections

class VideoPlaylistFragment : Fragment() {
    private lateinit var binding: VideoPlaylistFragmentBinding
    private val viewModel: VideoPlaylistViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        YLog.methodIn()
        binding = VideoPlaylistFragmentBinding.inflate(inflater, container, false)
        val adapter = VideoPlaylistAdapter {
            YLog.methodIn(it.toString())
            findNavController().navigate(
                VideoFragmentDirections.actionVideoToVideoPlaylistContents(it.id)
            )
        }
        binding.videoPlaylistRecycler.adapter = adapter
        binding.videoPlaylistRecycler.layoutManager = LinearLayoutManager(activity)

        binding.addButton.setOnClickListener {
            YLog.methodIn()
            findNavController().navigate(
                VideoFragmentDirections.actionVideoToPlaylistNameEditDialog()
            )
        }

        viewModel.videoPlaylist().observe(viewLifecycleOwner) {
            YLog.methodIn(it.toString())
            adapter.setItems(it)
        }
        return binding.root
    }
}