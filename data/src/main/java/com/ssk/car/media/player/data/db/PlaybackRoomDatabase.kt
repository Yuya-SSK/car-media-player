package com.ssk.car.media.player.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ssk.car.media.player.data.dao.PlaybackDao
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.media.RoomTypeConverter

@Database(entities = [Playback::class, PlaybackContent::class], version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class PlaybackRoomDatabase : RoomDatabase() {
    abstract fun playbackDao(): PlaybackDao
    companion object {
        private const val DB_NAME = "playback.db"
        @Volatile
        private var instance: PlaybackRoomDatabase? = null
        @JvmStatic
        fun getInstance(context: Context, name: String = DB_NAME) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                PlaybackRoomDatabase::class.java,
                name
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }
    }
}