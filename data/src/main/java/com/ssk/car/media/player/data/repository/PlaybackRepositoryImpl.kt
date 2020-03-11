package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaybackRepositoryImpl(
    private val playbackDao: PlaybackDao
): PlaybackRepository {

    companion object {
        @Volatile
        private var instance: PlaybackRepositoryImpl? = null

        @JvmStatic
        fun getInstance(playbackDao: PlaybackDao) =
            instance ?: synchronized(this) {
                instance ?: PlaybackRepositoryImpl(playbackDao).also { instance = it }
            }
    }

    private val defaultDispatcher = Dispatchers.IO

    override suspend fun playbackWithContents(): PlaybackWithContents {
        validate()
        return withContext(defaultDispatcher) {
            playbackDao.playbackWithContents().first()
        }
    }

    override suspend fun insertPlaybackContents(vararg uris: Uri) {
        val playbackContents = mutableListOf<PlaybackContent>()
        for (uri in uris) {
            playbackContents.add(PlaybackContent(uri = uri))
        }
        insertPlaybackContents(*playbackContents.toTypedArray())
    }

    override suspend fun insertPlaybackContents(vararg playbackContents: PlaybackContent) {
        validate()
        withContext(defaultDispatcher) {
            playbackDao.insertPlaybackContents(*playbackContents)
        }
    }

    override suspend fun updatePlayback(playback: Playback) {
        withContext(defaultDispatcher) {
            playbackDao.insertPlayback(playback.copy(id = 1L))
        }
    }

    override suspend fun updatePlaybackContents(vararg playbackContents: PlaybackContent) {
        validate()
        withContext(defaultDispatcher) {
            playbackDao.updatePlaybackContents(*playbackContents)
        }
    }

    override suspend fun deleteAll() =
        withContext(defaultDispatcher) {
            playbackDao.deleteAll()
        }

    private suspend fun validate() =
        withContext(defaultDispatcher) {
            if (playbackDao.playbackWithContents().isEmpty()) {
                playbackDao.insertPlayback(Playback(id = 1L))
            }
    }
}