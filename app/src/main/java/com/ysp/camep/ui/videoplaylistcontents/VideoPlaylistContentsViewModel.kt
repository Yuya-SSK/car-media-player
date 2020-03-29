package com.ysp.camep.ui.videoplaylistcontents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.Injection

class VideoPlaylistContentsViewModel(application: Application) : AndroidViewModel(application) {
    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)
    private val videoPlaylistContentsLiveData = MutableLiveData<List<VideoContent>>()

    private val _playlistContents = MutableLiveData<List<VideoContent>>()
    public val playlistContents = _playlistContents

    public fun loadPlaylistContents(playlistId: Long) {
    }
}