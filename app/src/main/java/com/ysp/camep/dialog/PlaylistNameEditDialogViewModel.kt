package com.ysp.camep.dialog

import android.app.Application
import androidx.lifecycle.*
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class PlaylistNameEditDialogViewModel(application: Application) : AndroidViewModel(application) {
    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)
    fun addPlaylist(playlistName: String) {
        YLog.methodIn(playlistName)
        viewModelScope.launch {
            YLog.d("addPlaylist 1")
            videoPlaylistRepository.insert(Playlist(name = playlistName))
            YLog.d("addPlaylist 2")
        }
        YLog.methodOut()
    }
    fun updatePlaylist(playlist: Playlist) {
        YLog.methodIn(playlist.toString())
        viewModelScope.launch {
            YLog.d("updatePlaylist 1")
            videoPlaylistRepository.update(playlist)
            YLog.d("updatePlaylist 2")
        }
        YLog.methodOut()
    }
}