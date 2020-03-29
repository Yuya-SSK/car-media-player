package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.entity.Playlist

interface PlaylistRepository {
    suspend fun playlists(): List<Playlist>

    suspend fun playlist(id: Long): Playlist

    suspend fun insert(vararg playlist: Playlist)

    suspend fun delete(vararg playlist: Playlist)

    suspend fun update(vararg playlist: Playlist)

    suspend fun deleteAll()
}