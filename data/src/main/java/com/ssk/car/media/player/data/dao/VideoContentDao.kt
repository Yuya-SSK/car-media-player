package com.ssk.car.media.player.data.dao

import android.net.Uri
import com.ssk.car.media.player.data.entity.VideoContent

interface VideoContentDao {
    suspend fun videoContents(): List<VideoContent>
    suspend fun videoContent(uri: Uri): VideoContent?
}