package com.ysp.camep.ui.videocontents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ssk.car.media.player.data.entity.VideoContent
import com.ysp.camep.Injection
import kotlinx.coroutines.launch

class VideoContentsViewModel(application: Application) : AndroidViewModel(application) {
    private val videoContentRepository = Injection.provideVideoContentRepository(application)
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