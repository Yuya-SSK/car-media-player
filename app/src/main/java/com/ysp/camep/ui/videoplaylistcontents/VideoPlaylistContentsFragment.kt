package com.ysp.camep.ui.videoplaylistcontents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.VideoPlaylistContentsFragmentBinding

class VideoPlaylistContentsFragment : Fragment() {
    private val args: VideoPlaylistContentsFragmentArgs by navArgs()
    private lateinit var binding: VideoPlaylistContentsFragmentBinding
    private val viewModel: VideoPlaylistContentsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        YLog.methodIn()
        binding = VideoPlaylistContentsFragmentBinding.inflate(inflater, container, false)
        val adapter = VideoPlaylistContentsAdapter {
            findNavController().navigate(
                VideoPlaylistContentsFragmentDirections.actionVideoPlaylistContentsToVideoPlayer(
                    listOf(it.uri).toTypedArray()
                )
            )
        }
        binding.videoPlaylistContentsRecycler.adapter = adapter
        binding.videoPlaylistContentsRecycler.layoutManager = LinearLayoutManager(activity)
        viewModel.loadPlaylistContents(args.playlistId)
        return binding.root
    }
}
