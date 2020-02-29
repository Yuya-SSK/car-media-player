package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import java.util.ArrayList

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

    override suspend fun playbackWithContents(): PlaybackWithContents? {
        return if (playbackDao.playbackWithContents().isNotEmpty()) playbackDao.playbackWithContents().first() else null
    }

    override suspend fun playbackWithContentsList(): List<PlaybackWithContents> {
        return playbackDao.playbackWithContents()
    }

    override suspend fun insertPlayback(playback: Playback) {
        playbackDao.insertPlayback(playback)
    }

    override suspend fun insertPlaybackContents(vararg uris: Uri) {
        val playbackContents = mutableListOf<PlaybackContent>()
        for (uri in uris) {
            playbackContents.add(PlaybackContent(uri = uri))
        }
        playbackDao.insertPlaybackContents(*playbackContents.toTypedArray())
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