package com.ssk.car.media.player.data.repository

import com.ssk.car.media.player.data.entity.VideoContent

interface VideoContentRepository {
    suspend fun videoContentList(): List<VideoContent>
}