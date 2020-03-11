package com.ysp.camep.ui.videoplaylistcontents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssk.car.media.player.data.entity.VideoContent
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.VideoPlaylistContentsFragmentBinding

class VideoPlaylistContentsFragment : Fragment(), VideoPlaylistContentsAdapter.ItemClickListener {
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
        val adapter = VideoPlaylistContentsAdapter(this)
        binding.videoPlaylistContentsRecycler.adapter = adapter
        binding.videoPlaylistContentsRecycler.layoutManager = LinearLayoutManager(activity)
//        viewModel.videoPlaylist().observe(viewLifecycleOwner) {
//            adapter.setItems(it)
//        }
        return binding.root
    }

    override fun onItemClick(videoContent: VideoContent) {
        YLog.methodIn(videoContent.toString())
//        findNavController().navigate(
//            VideoFragmentDirections.actionVideoToVideoPlayer(
//                listOf(playlist.uris).toTypedArray()))
    }
}