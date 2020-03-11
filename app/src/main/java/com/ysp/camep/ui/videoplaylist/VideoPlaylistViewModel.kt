package com.ysp.camep.ui.videoplaylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.data.repository.PlaylistRepository
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class VideoPlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)
    private val videoPlaylistLiveData = MutableLiveData<List<Playlist>>()

    fun videoPlaylist(): LiveData<List<Playlist>> {
        return videoPlaylistLiveData
    }

    fun loadVideoPlaylist() {
        viewModelScope.launch {
            videoPlaylistLiveData.setValue(videoPlaylistRepository.playlists())
        }
    }
}