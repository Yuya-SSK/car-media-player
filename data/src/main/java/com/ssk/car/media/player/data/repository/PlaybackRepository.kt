package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents

interface PlaybackRepository {
    suspend fun playbackWithContents(): PlaybackWithContents

    suspend fun insertContents(vararg uris: Uri)

    suspend fun insertContents(vararg contents: PlaybackContent)

    suspend fun updatePlayback(playback: Playback)

    suspend fun updateContents(vararg contents: PlaybackContent)

    suspend fun deleteAll()
}