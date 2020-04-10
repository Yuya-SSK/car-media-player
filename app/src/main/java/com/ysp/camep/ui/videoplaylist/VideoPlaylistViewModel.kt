package com.ysp.camep.ui.videoplaylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.ysp.camep.Injection

class VideoPlaylistViewModel(application: Application) : AndroidViewModel(application) {

    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)

    val videoPlaylist = videoPlaylistRepository.playlistsFlow().asLiveData()
}