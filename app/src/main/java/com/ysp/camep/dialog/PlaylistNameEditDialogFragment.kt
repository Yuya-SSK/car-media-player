package com.ysp.camep.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.databinding.PlaylistNameEditDialogBinding
import com.ysp.camep.ui.videoplaylist.VideoPlaylistViewModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PlaylistNameEditDialogFragment : DialogFragment() {
    private val args: PlaylistNameEditDialogFragmentArgs by navArgs()
    private val viewModel: PlaylistNameEditDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val binding = PlaylistNameEditDialogBinding.inflate(
            LayoutInflater.from(requireContext()), null, false)
        val playlist = args.playlist
        binding.playlistName = playlist?.name ?: "My Playlist"
        builder.setView(binding.root)
            .setTitle("タイトル")
            .setPositiveButton("OK") { _, _ ->
                dismiss()
                if (null == playlist) {
                    viewModel.addPlaylist(binding.playlistName!!)
                } else {
                    playlist.name = binding.playlistName!!
                    viewModel.updatePlaylist(playlist)
                }
            }
            .setNegativeButton("キャンセル") { _, _ ->
                dismiss()
            }
        return builder.create()
    }
}