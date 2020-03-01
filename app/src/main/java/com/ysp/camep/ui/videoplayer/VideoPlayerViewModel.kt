package com.ysp.camep.ui.videoplayer

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import com.ssk.car.media.player.data.entity.PlaybackWithContents
import com.ssk.car.media.player.data.repository.PlaybackRepository
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.Injection
import com.ysp.camep.R
import kotlinx.coroutines.launch
import java.util.*

class VideoPlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val playbackRepository: PlaybackRepository = Injection.providePlaybackRepository(application)
    private val player = ExoPlayerFactory.newSimpleInstance(application, DefaultTrackSelector())
    private val dataSourceFactory: DataSource.Factory
    private val extractorMediaSourceFactory: ExtractorMediaSource.Factory
    private lateinit var playbackWithContents: PlaybackWithContents
    init {
        YLog.methodIn()
        player.addListener(EventListener())
        player.repeatMode = Player.REPEAT_MODE_ALL
        dataSourceFactory = DefaultDataSourceFactory(
            application,
            Util.getUserAgent(application, application.getString(R.string.app_name)),
            null)
        extractorMediaSourceFactory = ExtractorMediaSource.Factory(dataSourceFactory)
    }

    override fun onCleared() {
        YLog.methodIn()
        player.release()
    }

    fun getPlayer(): Player {
        YLog.methodIn()
        return player
    }

    fun prepare(uris: List<Uri>) {
        YLog.methodIn()
        viewModelScope.launch {
            playbackRepository.insertPlayback(Playback())
            playbackRepository.insertPlaybackContents(*uris.toTypedArray())
        }
    }

    fun play() {
        YLog.methodIn()
        viewModelScope.launch {
            playbackWithContents = playbackRepository.playbackWithContents()!!
            play(
                ConcatenatingMediaSource(*playbackContentsToMediaSources(playbackWithContents.contents).toTypedArray()),
                playbackWithContents.playback.windowIndex,
                playbackWithContents.playback.position)
        }
    }

    private fun play(mediaSource: MediaSource, windowIndex: Int = 0, position: Long = 0) {
        YLog.methodIn("windowIndex=$windowIndex position=$position")
        player.prepare(mediaSource)
        player.seekTo(windowIndex, position)
        player.playWhenReady = true
    }

    fun pause() {
        YLog.methodIn()
        player.playWhenReady = false
        playbackWithContents.playback.windowIndex = player.currentWindowIndex
        playbackWithContents.playback.position = player.currentPosition
        viewModelScope.launch {
            playbackRepository.updatePlayback(playbackWithContents.playback)
        }
    }

    private fun playbackContentsToMediaSources(playbackContent: List<PlaybackContent>): List<MediaSource> {
        val sources: MutableList<MediaSource> = ArrayList()
        playbackContent.forEach {
            sources.add(extractorMediaSourceFactory.createMediaSource(it.uri))
        }
        return sources
    }

    private inner class EventListener : Player.EventListener {
        override fun onTimelineChanged(
            timeline: Timeline,
            manifest: Any?,
            reason: Int
        ) {
            YLog.methodIn("$reason")
        }

        override fun onTracksChanged(
            trackGroups: TrackGroupArray,
            trackSelections: TrackSelectionArray
        ) {
            YLog.methodIn("onTracksChanged : ${player.currentPeriodIndex} ${player.currentWindowIndex}")
        }

        override fun onLoadingChanged(isLoading: Boolean) {
            YLog.methodIn("onLoadingChanged : $isLoading")
        }

        override fun onPlayerStateChanged(
            playWhenReady: Boolean,
            playbackState: Int
        ) {
            YLog.methodIn("onPlayerStateChanged : $playWhenReady $playbackState")
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            YLog.methodIn("onRepeatModeChanged : $repeatMode")
        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
            YLog.methodIn("onRepeatModeChanged : $shuffleModeEnabled")
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            YLog.methodIn("onRepeatModeChanged : ${error.message}")
        }

        override fun onPositionDiscontinuity(reason: Int) {
            YLog.methodIn("onPositionDiscontinuity : $reason")
        }

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
            YLog.methodIn("onPlaybackParametersChanged : $playbackParameters")
        }

        override fun onSeekProcessed() {
            YLog.methodIn("onSeekProcessed : currentPosition : ${player.currentPosition}")
        }
    }
}