package com.ysp.camep

import android.content.Context
import com.ssk.car.media.player.data.dao.VideoContentDaoImpl
import com.ssk.car.media.player.data.db.PlaybackRoomDatabase
import com.ssk.car.media.player.data.db.PlaylistRoomDatabase
import com.ssk.car.media.player.data.repository.PlaybackRepository
import com.ssk.car.media.player.data.repository.PlaybackRepositoryImpl.Companion.getInstance
import com.ssk.car.media.player.data.repository.PlaylistRepository
import com.ssk.car.media.player.data.repository.PlaylistRepositoryImpl.Companion.getInstance
import com.ssk.car.media.player.data.repository.VideoContentRepository
import com.ssk.car.media.player.data.repository.VideoContentRepositoryImpl.Companion.getInstance
import com.ssk.car.media.player.device.power.AndroidPowerManager
import com.ssk.car.media.player.device.power.PowerManager
import com.ssk.car.media.player.device.power.PowerManagerImpl.Companion.getInstance

object Injection {
    fun providePlaybackRepository(context: Context): PlaybackRepository {
        return getInstance(PlaybackRoomDatabase.getInstance(context).playbackDao())
    }

    fun providePlaylistRepository(context: Context): PlaylistRepository {
        return getInstance(PlaylistRoomDatabase.getInstance(context).playlistDao())
    }

    fun provideVideoContentRepository(context: Context): VideoContentRepository {
        return getInstance(VideoContentDaoImpl(context))
    }

    fun providePowerManager(context: Context): PowerManager {
        return getInstance(AndroidPowerManager(context))
    }
}