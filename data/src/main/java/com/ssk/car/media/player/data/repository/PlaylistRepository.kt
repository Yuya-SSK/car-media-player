package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.entity.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun playlistsFlow(): Flow<List<Playlist>>

    suspend fun playlistById(id: Long): Playlist

    suspend fun playlists(): List<Playlist>

    suspend fun insert(vararg playlist: Playlist)

    suspend fun update(vararg playlist: Playlist)

    suspend fun delete(vararg playlist: Playlist)

    suspend fun deleteAll()
}