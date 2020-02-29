package com.ysp.camep.ui.videoplaylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ysp.camep.databinding.VideoPlaylistFragmentBinding

class VideoPlaylistFragment : Fragment() {
    private lateinit var binding: VideoPlaylistFragmentBinding
    private val viewModel: VideoPlaylistViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoPlaylistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}