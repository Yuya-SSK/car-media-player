package com.ysp.camep.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.PlaylistNameEditDialogBinding
import com.ysp.camep.ui.videoplaylist.VideoPlaylistViewModel

class PlaylistNameEditDialogFragment : DialogFragment() {

    private val viewModel: VideoPlaylistViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val binding = PlaylistNameEditDialogBinding.inflate(
            LayoutInflater.from(requireContext()), null, false)
        builder.setView(binding.root)
            .setTitle("タイトル")
            .setPositiveButton("OK") { _, _ ->
                YLog.methodIn()
                viewModel.addPlaylist(binding.text!!)
            }
        return builder.create()
    }
}