package com.ysp.camep.ui.videoplaylist

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        binding.button.setOnClickListener {
            findNavController().navigate(
                VideoFragmentDirections.actionVideoToVideoPlayer(
                    listOf(Uri.EMPTY).toTypedArray()))
        }
        return binding.root
    }
}