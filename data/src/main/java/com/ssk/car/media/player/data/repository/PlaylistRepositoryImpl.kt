package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.dao.PlaylistDao
import com.ssk.car.media.player.data.entity.Playlist

class PlaylistRepositoryImpl(
        private val playlistDao: PlaylistDao): PlaylistRepository {

    companion object {
        @Volatile
        private var instance: PlaylistRepositoryImpl? = null
        @JvmStatic
        fun getInstance(playlistDao: PlaylistDao) = instance ?: synchronized(this) {
            instance ?: PlaylistRepositoryImpl(playlistDao).also { instance = it }
        }
    }

    override suspend fun playlists(): List<Playlist> {
        return playlistDao.playlists()
    }

    override suspend fun insert(vararg playlist: Playlist) {
        playlistDao.insert(*playlist)
    }

    override suspend fun delete(vararg playlist: Playlist) {
        playlistDao.delete(*playlist)
    }

    override suspend fun update(vararg playlist: Playlist) {
        playlistDao.update(*playlist)
    }

    override suspend fun deleteAll() {
        playlistDao.deleteAll()
    }
}