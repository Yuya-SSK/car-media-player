package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents

interface PlaybackRepository {
    suspend fun playbackWithContents(): PlaybackWithContents?

    suspend fun playbackWithContentsList(): List<PlaybackWithContents>

    suspend fun insertPlayback(playback: Playback)

    suspend fun insertPlaybackContents(vararg uris: Uri)

    suspend fun insertPlaybackContents(vararg playbackContents: PlaybackContent)

    suspend fun updatePlayback(playback: Playback)

    suspend fun updatePlaybackContents(vararg playbackContents: PlaybackContent)

    suspend fun deletePlayback(playback: Playback)

    suspend fun deleteAll()
}