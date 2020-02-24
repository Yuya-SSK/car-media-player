package com.ssk.car.media.player.data.repository

import android.content.Context
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ssk.car.media.player.data.db.PlaybackRoomDatabase
import com.ssk.car.media.player.data.entity.Playback
import com.ssk.car.media.player.data.entity.PlaybackContent
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PlaybackRepositoryImplTest {
    companion object {
        private val URI_DUMMY: Uri = Uri.parse("content://media/external/images")
    }

    private lateinit var database: PlaybackRoomDatabase
    private lateinit var repository: PlaybackRepositoryImpl

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PlaybackRoomDatabase::class.java).build()
        repository = PlaybackRepositoryImpl(database.playbackDao())
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertPlayback() = runBlocking {
        repository.insertPlayback(Playback())
        assertThat(repository.playbackWithContents()).hasSize(1)
        assertThat(repository.playbackWithContents()[0].contents).isEmpty()
        return@runBlocking
    }

    @Test
    fun insertPlaybackContents() = runBlocking {
        repository.insertPlayback(Playback())

        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents()[0].contents).hasSize(1)
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents()[0].contents).hasSize(2)
        return@runBlocking
    }

    @Test
    fun updatePlayback() = runBlocking {
        repository.insertPlayback(Playback())
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))

        val playback = repository.playbackWithContents()[0].playback
        playback.windowIndex = 1
        playback.position = 55555
        repository.updatePlayback(playback)
        assertThat(repository.playbackWithContents()[0].playback.windowIndex).isEqualTo(1)
        assertThat(repository.playbackWithContents()[0].playback.position).isEqualTo(55555)
        return@runBlocking
    }

    @Test
    fun updatePlaybackContents() = runBlocking {
        repository.insertPlayback(Playback())
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))

        val playbackContent = repository.playbackWithContents()[0].contents[0]
        playbackContent.isPlayed = true
        repository.updatePlaybackContents(playbackContent)
        assertThat(repository.playbackWithContents()[0].contents[0].isPlayed).isTrue()
        return@runBlocking
    }

    @Test
    fun delete() = runBlocking {
        repository.insertPlayback(Playback())
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))
        repository.insertPlaybackContents(PlaybackContent(uri = URI_DUMMY))

        repository.deletePlayback(repository.playbackWithContents()[0].playback)
        assertThat(repository.playbackWithContents()).isEmpty()
    }
}