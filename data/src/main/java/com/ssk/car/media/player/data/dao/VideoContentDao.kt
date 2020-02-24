package com.ssk.car.media.player.data.dao

import com.ssk.car.media.player.data.entity.VideoContent

interface VideoContentDao {
    suspend fun videoContents(): List<VideoContent>
}