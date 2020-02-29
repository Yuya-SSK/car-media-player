package com.ysp.camep.ui.videocontents

import android.app.Application
import androidx.lifecycle.*
import com.ssk.car.media.player.data.entity.VideoContent
import com.ssk.car.media.player.data.repository.VideoContentRepository
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class VideoContentsViewModel(application: Application) : AndroidViewModel(application) {
    private val videoContentRepository: VideoContentRepository = Injection.provideVideoContentRepository(application)
    private val videoContentsLiveData = MutableLiveData<List<VideoContent>>()

    fun videoContents(): LiveData<List<VideoContent>> {
        return videoContentsLiveData
    }

    fun loadVideoContents() {
        viewModelScope.launch {
            videoContentsLiveData.setValue(videoContentRepository.videoContentList())
        }
    }
}