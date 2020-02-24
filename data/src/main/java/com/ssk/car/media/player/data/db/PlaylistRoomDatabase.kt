package com.ssk.car.media.player.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ssk.car.media.player.data.dao.PlaylistDao
import com.ssk.car.media.player.data.entity.Playlist
import com.ssk.car.media.player.data.media.RoomTypeConverter

@Database(entities = [Playlist::class], version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class PlaylistRoomDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    companion object {
        private const val DB_NAME = "playlist.db"
        @Volatile
        private var instance: PlaylistRoomDatabase? = null
        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistRoomDatabase::class.java,
                    DB_NAME
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }
    }
}