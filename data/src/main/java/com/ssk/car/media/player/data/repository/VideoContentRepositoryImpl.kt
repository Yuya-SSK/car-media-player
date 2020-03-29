package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.dao.VideoContentDao
import com.ssk.car.media.player.data.entity.VideoContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoContentRepositoryImpl(
        private val videoContentDao: VideoContentDao): VideoContentRepository {

    private val defaultDispatcher = Dispatchers.IO

    companion object {
        @Volatile
        private var instance: VideoContentRepositoryImpl? = null
        @JvmStatic
        fun getInstance(videoContentDao: VideoContentDao):VideoContentRepositoryImpl = instance ?: synchronized(this) {
            instance ?: VideoContentRepositoryImpl(videoContentDao).also { instance = it }
        }
    }

    override suspend fun videoContentList(): List<VideoContent> =
        withContext(defaultDispatcher) {
            videoContentDao.videoContents()
        }

    override suspend fun videoContent(uri: Uri): VideoContent? =
        withContext(defaultDispatcher) {
            videoContentDao.videoContent(uri)
        }
}