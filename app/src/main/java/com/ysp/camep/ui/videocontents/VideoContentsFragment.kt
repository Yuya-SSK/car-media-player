package com.ysp.camep.ui.videocontents

import android.Manifest
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
import com.ysp.camep.databinding.VideoContentsFragmentBinding
import com.ysp.camep.ui.video.VideoFragmentDirections
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class VideoContentsFragment : Fragment() {
    private lateinit var binding: VideoContentsFragmentBinding
    private val viewModel: VideoContentsViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        YLog.methodIn()
        binding = VideoContentsFragmentBinding.inflate(inflater, container, false)
        val adapter = VideoContentsAdapter {
            YLog.methodIn(it.toString())
            findNavController().navigate(
                    VideoFragmentDirections.actionVideoToVideoPlayer(
                            listOf(it.uri).toTypedArray()))
        }
        binding.videoContentsRecycler.adapter = adapter
        binding.videoContentsRecycler.layoutManager = LinearLayoutManager(activity)
        viewModel.videoContents().observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        loadVideoContentsWithPermissionCheck()
        return binding.root
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray) {
        YLog.methodIn()
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    fun loadVideoContents() {
        YLog.methodIn()
        viewModel.loadVideoContents()
    }
}