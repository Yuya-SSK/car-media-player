package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.dao.VideoContentDao
import com.ssk.car.media.player.data.entity.VideoContent

class VideoContentRepositoryImpl(
        private val videoContentDao: VideoContentDao): VideoContentRepository {

    companion object {
        @Volatile
        private var instance: VideoContentRepositoryImpl? = null
        @JvmStatic
        fun getInstance(videoContentDao: VideoContentDao):VideoContentRepositoryImpl = instance ?: synchronized(this) {
            instance ?: VideoContentRepositoryImpl(videoContentDao).also { instance = it }
        }
    }

    override suspend fun videoContentList(): List<VideoContent> {
        return videoContentDao.videoContents()
    }
}