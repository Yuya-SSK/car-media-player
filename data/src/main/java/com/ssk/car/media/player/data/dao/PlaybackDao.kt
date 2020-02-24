package com.ssk.car.media.player.data.dao

import androidx.room.*
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents

@Dao
interface PlaybackDao {
    @Transaction
    @Query("SELECT * FROM playback_table")
    fun playbackWithContents(): List<PlaybackWithContents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayback(playback: Playback)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaybackContents(vararg playbackContents: PlaybackContent)

    @Update
    fun updatePlayback(playback: Playback)

    @Update
    fun updatePlaybackContents(vararg playbackContents: PlaybackContent)

    @Delete
    fun deletePlayback(playback: Playback)

    @Query("DELETE FROM playback_table")
    fun deleteAll()
}
