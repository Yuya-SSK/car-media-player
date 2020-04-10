package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.dao.PlaylistDao
import com.ssk.car.media.player.data.entity.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PlaylistRepositoryImpl(
    private val playlistDao: PlaylistDao
) : PlaylistRepository {

    companion object {
        @Volatile
        private var instance: PlaylistRepositoryImpl? = null

        @JvmStatic
        fun getInstance(playlistDao: PlaylistDao) = instance ?: synchronized(this) {
            instance ?: PlaylistRepositoryImpl(playlistDao).also { instance = it }
        }
    }

    private val defaultDispatcher = Dispatchers.IO

    override suspend fun playlistById(id: Long): Playlist {
        return withContext(defaultDispatcher) {
            playlistDao.playlistById(id)
        }
    }

    override suspend fun playlists(): List<Playlist> {
        return withContext(defaultDispatcher) {
            playlistDao.playlists()
        }
    }

    override suspend fun playlistsFlow() =
        playlistDao.playlistsFlow()

    override suspend fun insert(vararg playlist: Playlist) {
        withContext(defaultDispatcher) {
            playlistDao.insert(*playlist)
        }
    }

    override suspend fun update(vararg playlist: Playlist) {
        withContext(defaultDispatcher) {
            playlistDao.update(*playlist)
        }
    }

    override suspend fun delete(vararg playlist: Playlist) {
        withContext(defaultDispatcher) {
            playlistDao.delete(*playlist)
        }
    }

    override suspend fun deleteAll() {
        withContext(defaultDispatcher) {
            playlistDao.deleteAll()
        }
    }
}