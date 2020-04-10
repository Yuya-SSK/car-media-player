package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import kotlinx.coroutines.flow.Flow

interface PlaybackRepository {

    suspend fun playback(): Playback

    suspend fun playbackFlow(): Flow<Playback>

    suspend fun playbackWithContents(): PlaybackWithContents

    suspend fun playbackWithContentsFlow(): Flow<PlaybackWithContents>

    suspend fun insertContents(vararg uris: Uri)

    suspend fun insertContents(vararg contents: PlaybackContent)

    suspend fun updatePlayback(playback: Playback)

    suspend fun updateContents(vararg contents: PlaybackContent)

    suspend fun deleteContents(vararg contents: PlaybackContent)
}