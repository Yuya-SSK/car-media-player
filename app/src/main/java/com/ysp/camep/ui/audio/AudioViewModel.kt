package com.ysp.camep.ui.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ssk.car.media.player.log.YLog

class AudioViewModel : ViewModel() {
    val text: LiveData<String> = liveData {
        YLog.methodIn()
        emit("This is notifications Fragment")
    }
}