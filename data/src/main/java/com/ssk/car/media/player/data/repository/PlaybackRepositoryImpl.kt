package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

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

    init {
        playbackDao.init()
    }

    private val defaultDispatcher = Dispatchers.IO

    override fun playbackFlow() = playbackDao.playbackFlow()

    override fun playbackWithContentsFlow() = playbackDao.playbackWithContentFlow()

    override suspend fun playback(): Playback {
        return withContext(defaultDispatcher) {
            playbackDao.playback()
        }
    }

    override suspend fun playbackWithContents(): PlaybackWithContents {
        return withContext(defaultDispatcher) {
            playbackDao.playbackWithContents()
        }
    }

    override suspend fun insertContents(vararg uris: Uri) {
        val playbackContents = mutableListOf<PlaybackContent>()
        for (uri in uris) {
            playbackContents.add(PlaybackContent(uri = uri))
        }
        insertContents(*playbackContents.toTypedArray())
    }

    override suspend fun insertContents(vararg contents: PlaybackContent) {
        withContext(defaultDispatcher) {
            playbackDao.insertContents(*contents)
        }
    }

    override suspend fun updatePlayback(playback: Playback) {
        withContext(defaultDispatcher) {
            playbackDao.updatePlayback(playback.copy(id = 1L))
        }
    }

    override suspend fun updateContents(vararg contents: PlaybackContent) {
        withContext(defaultDispatcher) {
            playbackDao.updateContents(*contents)
        }
    }

    override suspend fun deleteContents(vararg contents: PlaybackContent) {
        withContext(defaultDispatcher) {
            playbackDao.deleteContents(*contents)
        }
    }
}