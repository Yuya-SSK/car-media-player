package com.ssk.car.media.player.data.dao

import androidx.room.*
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaybackDao {

    @Query("INSERT INTO playback_table VALUES (1, 0, 0)")
    fun init()

    @Query("SELECT * FROM playback_table where playback_id = 1")
    fun playback(): Playback

    @Query("SELECT * FROM playback_table where playback_id = 1")
    fun playbackFlow(): Flow<Playback>

    @Transaction
    @Query("SELECT * FROM playback_table where playback_id = 1")
    fun playbackWithContents(): PlaybackWithContents

    @Transaction
    @Query("SELECT * FROM playback_table where playback_id = 1")
    fun playbackWithContentFlow(): Flow<PlaybackWithContents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContents(vararg contents: PlaybackContent)

    @Update
    fun updatePlayback(playback: Playback)

    @Update
    fun updateContents(vararg contents: PlaybackContent)

    @Delete
    fun deleteContents(vararg contents: PlaybackContent)
}
