package com.ssk.car.media.player.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "playback_table",
        indices = [Index("playback_id")])
data class Playback(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "playback_id")
        val id: Long = 1L,
        @ColumnInfo(name = "window_index") var windowIndex: Int = 0,
        @ColumnInfo(name = "position") var position: Long = 0L
)