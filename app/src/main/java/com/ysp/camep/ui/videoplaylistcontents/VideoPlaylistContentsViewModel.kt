package com.ysp.camep.ui.videoplaylistcontents

import android.app.Application
import androidx.lifecycle.*
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class VideoPlaylistContentsViewModel(application: Application) : AndroidViewModel(application) {
    private val videoPlaylistRepository = Injection.providePlaylistRepository(application)
    private val videoPlaylistContentsLiveData = MutableLiveData<List<VideoContent>>()
}