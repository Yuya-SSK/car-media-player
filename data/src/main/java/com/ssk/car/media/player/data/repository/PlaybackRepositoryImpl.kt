package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents

class PlaybackRepositoryImpl(
        private val playbackDao: PlaybackDao): PlaybackRepository {

    companion object {
        @Volatile
        private var instance: PlaybackRepositoryImpl? = null
        @JvmStatic
        fun getInstance(playbackDao: PlaybackDao) = instance ?: synchronized(this) {
            instance ?: PlaybackRepositoryImpl(playbackDao).also { instance = it }
        }
    }

    override suspend fun playbackWithContents(): List<PlaybackWithContents> {
        return playbackDao.playbackWithContents()
    }

    override suspend fun insertPlayback(playback: Playback) {
        playbackDao.insertPlayback(playback)
    }

    override suspend fun insertPlaybackContents(vararg playbackContents: PlaybackContent) {
        playbackDao.insertPlaybackContents(*playbackContents)
    }

    override suspend fun updatePlayback(playback: Playback) {
        playbackDao.updatePlayback(playback)
    }

    override suspend fun updatePlaybackContents(vararg playbackContents: PlaybackContent) {
        playbackDao.updatePlaybackContents(*playbackContents)
    }

    override suspend fun deletePlayback(playback: Playback) {
        playbackDao.deletePlayback(playback)
    }

    override suspend fun deleteAll() {
        playbackDao.deleteAll()
    }
}