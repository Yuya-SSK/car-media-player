package com.ssk.car.media.player.data.repository

import android.net.Uri
import com.ssk.car.media.player.data.entity.VideoContent

interface VideoContentRepository {
    suspend fun videoContentList(): List<VideoContent>
    suspend fun videoContent(uri: Uri): VideoContent?
}