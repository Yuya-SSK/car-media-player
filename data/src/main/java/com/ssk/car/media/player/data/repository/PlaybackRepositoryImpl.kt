package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.ArrayList

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

    override suspend fun playbackWithContents(): PlaybackWithContents? {
        val playbackWithContentsList = playbackWithContentsList()
        return if (playbackWithContentsList.isNotEmpty()) playbackWithContentsList.first() else null
    }

    override suspend fun playbackWithContentsList() =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.playbackWithContents()
        }

    override suspend fun insertPlayback(playback: Playback) =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.insertPlayback(playback)
        }

    override suspend fun insertPlaybackContents(vararg uris: Uri) {
        val playbackContents = mutableListOf<PlaybackContent>()
        for (uri in uris) {
            playbackContents.add(PlaybackContent(uri = uri))
        }
        insertPlaybackContents(*playbackContents.toTypedArray())
    }

    override suspend fun insertPlaybackContents(vararg playbackContents: PlaybackContent) =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.insertPlaybackContents(*playbackContents)
        }

    override suspend fun updatePlayback(playback: Playback) =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.updatePlayback(playback)
        }

    override suspend fun updatePlaybackContents(vararg playbackContents: PlaybackContent) =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.updatePlaybackContents(*playbackContents)
        }

    override suspend fun deletePlayback(playback: Playback) =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.deletePlayback(playback)
        }

    override suspend fun deleteAll() =
        withContext(defaultDispatcher) {
            YLog.methodIn()
            playbackDao.deleteAll()
        }
}