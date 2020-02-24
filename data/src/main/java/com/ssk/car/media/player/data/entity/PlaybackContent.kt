package com.ssk.car.media.player.data.entity

import android.net.Uri
import androidx.room.*

@Entity(
        tableName = "playback_content_table",
        indices = [Index("playback_content_id")],
        foreignKeys = [ForeignKey(
                entity = Playback::class,
                parentColumns = arrayOf("playback_id"),
                childColumns = arrayOf("playback_id"),
                onDelete = ForeignKey.CASCADE)]
)
data class PlaybackContent(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "playback_content_id")
        val id: Long = 0,
        @ColumnInfo(name = "uri") val uri: Uri,
        @ColumnInfo(name = "is_played") var isPlayed: Boolean = false,
        @ColumnInfo(name = "playback_id") val playback_id: Long = 1L
)