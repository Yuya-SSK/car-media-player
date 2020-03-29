package com.ysp.camep.ui.videoplaylist

import android.app.Application
import androidx.lifecycle.*
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class VideoPlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)
    private val videoPlaylistLiveData = MutableLiveData<List<Playlist>>()
    init {
        viewModelScope.launch {
            videoPlaylistLiveData.value = videoPlaylistRepository.playlists()
        }
    }
    fun videoPlaylist(): LiveData<List<Playlist>> {
        YLog.methodIn()
        return videoPlaylistLiveData
    }
    fun addPlaylist(name: String) {
        YLog.methodIn(name)
        viewModelScope.launch {
            YLog.d("addPlaylist1")
            videoPlaylistRepository.insert(Playlist(name = name))
            YLog.d("addPlaylist2")
            videoPlaylistLiveData.value = videoPlaylistRepository.playlists()
            YLog.d("addPlaylist3")
        }
        YLog.methodOut()
    }
}