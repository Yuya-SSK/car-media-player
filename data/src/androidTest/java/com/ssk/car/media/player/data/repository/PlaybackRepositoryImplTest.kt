package com.ssk.car.media.player.data.repository

import android.content.Context
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ssk.car.media.player.data.db.PlaybackRoomDatabase
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
    fun playbackWithContents() = runBlocking {

        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(0)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(0L)
        assertThat(repository.playbackWithContents().contents).isEmpty()

        repository.deleteAll()

        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(0)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(0L)
        assertThat(repository.playbackWithContents().contents).isEmpty()

        return@runBlocking
    }

    @Test
    fun insertPlaybackContents() = runBlocking {

        repository.insertContents(PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents().contents).hasSize(1)
        repository.insertContents(PlaybackContent(uri = URI_DUMMY), PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents().contents).hasSize(3)

        repository.insertContents(URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(4)
        repository.insertContents(URI_DUMMY, URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(6)

        repository.deleteAll()

        repository.insertContents(URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(1)
        repository.insertContents(URI_DUMMY, URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(3)

        return@runBlocking
    }

    @Test
    fun updatePlayback() = runBlocking {

        val playback = repository.playbackWithContents().playback
        playback.windowIndex = 11111
        playback.position = 55555
        repository.updatePlayback(playback)
        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(11111)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(55555)
        assertThat(repository.playbackWithContents().contents).isEmpty()

        repository.deleteAll()

        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(0)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(0L)
        assertThat(repository.playbackWithContents().contents).isEmpty()

        return@runBlocking
    }

    @Test
    fun updatePlaybackContents() = runBlocking {
        repository.insertContents(URI_DUMMY)
        repository.insertContents(URI_DUMMY)

        val playbackContent0 = repository.playbackWithContents().contents[0]
        playbackContent0.isPlayed = true
        repository.updateContents(playbackContent0)
        assertThat(repository.playbackWithContents().contents[0].isPlayed).isTrue()
        assertThat(repository.playbackWithContents().contents[1].isPlayed).isFalse()

        val playbackContent1 = repository.playbackWithContents().contents[1]
        playbackContent1.isPlayed = true
        repository.updateContents(playbackContent1)
        assertThat(repository.playbackWithContents().contents[0].isPlayed).isTrue()
        assertThat(repository.playbackWithContents().contents[1].isPlayed).isTrue()

        repository.deleteAll()

        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(0)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(0L)
        assertThat(repository.playbackWithContents().contents).isEmpty()

        return@runBlocking
    }
}