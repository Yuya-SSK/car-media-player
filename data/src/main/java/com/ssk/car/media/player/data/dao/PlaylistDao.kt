package com.ssk.car.media.player.data.dao

import androidx.room.*
import com.ssk.car.media.player.data.entity.Playlist

@Dao
interface PlaylistDao {
    @Query("SELECT * from playlist_table ORDER BY id ASC")
    fun playlists(): List<Playlist>

    @Query("SELECT * from playlist_table WHERE name = :name")
    fun playlistByName(name: String): Playlist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg playlist: Playlist)

    @Update
    fun update(vararg playlist: Playlist)

    @Delete
    fun delete(vararg playlist: Playlist)

    @Query("DELETE FROM playlist_table")
    fun deleteAll()
}
