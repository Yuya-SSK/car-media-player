package com.ysp.camep.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.VideoFragmentBinding

class VideoFragment : Fragment() {

    private lateinit var binding: VideoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        YLog.methodIn()
        binding = VideoFragmentBinding.inflate(layoutInflater, container, false)
        binding.pager.adapter = VideoTabAdapter(childFragmentManager, requireContext())
        binding.tabLayout.setupWithViewPager(binding.pager)
        return binding.root
    }
}