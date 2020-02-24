package com.ssk.car.media.player.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class PlaybackWithContents {
    @Embedded
    lateinit var playback: Playback

    @Relation(parentColumn = "playback_id", entityColumn = "playback_id")
    lateinit var contents: List<PlaybackContent>
}