package com.ssk.car.media.player.data.repository

import android.content.Context
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ssk.car.media.player.data.db.PlaybackRoomDatabase
import com.ssk.car.media.player.data.entity.PlaybackContent
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
    fun playbackFlow() = runBlocking {
        var count = 0
        val job = launch {
            repository.playbackFlow().collect {
                if (count == 0) {
                    assertThat(it.id).isEqualTo(1L)
                    assertThat(it.windowIndex).isEqualTo(0)
                    assertThat(it.position).isEqualTo(0L)
                } else {
                    assertThat(it.id).isEqualTo(1L)
                    assertThat(it.windowIndex).isEqualTo(11111)
                    assertThat(it.position).isEqualTo(55555L)
                }
            }
        }
        delay(100)
        val playback = repository.playbackWithContents().playback
        playback.windowIndex = 11111
        playback.position = 55555L
        count++;
        repository.updatePlayback(playback)
        delay(100)
        job.cancelAndJoin()
        return@runBlocking
    }

    @Test
    fun playbackWithContentsFlow() = runBlocking {
        var count = 0
        val job = launch {
            repository.playbackWithContentsFlow().collect {
                if (count == 0) {
                    assertThat(it.playback.id).isEqualTo(1L)
                    assertThat(it.playback.windowIndex).isEqualTo(0)
                    assertThat(it.playback.position).isEqualTo(0L)
                    assertThat(it.contents).isEmpty()
                } else if (count == 1) {
                    assertThat(it.playback.id).isEqualTo(1L)
                    assertThat(it.playback.windowIndex).isEqualTo(11111)
                    assertThat(it.playback.position).isEqualTo(55555L)
                    assertThat(it.contents).isEmpty()
                } else {
                    assertThat(it.playback.id).isEqualTo(1L)
                    assertThat(it.playback.windowIndex).isEqualTo(11111)
                    assertThat(it.playback.position).isEqualTo(55555L)
                    assertThat(it.contents).hasSize(2)
                }
            }
        }
        delay(100)
        val playback = repository.playbackWithContents().playback
        playback.windowIndex = 11111
        playback.position = 55555L
        count++;
        repository.updatePlayback(playback)
        delay(100)
        count++;
        repository.insertContents(URI_DUMMY, URI_DUMMY)
        delay(100)
        job.cancelAndJoin()
        return@runBlocking
    }

    @Test
    fun playback() = runBlocking {
        assertThat(repository.playback().id).isEqualTo(1L)
        assertThat(repository.playback().windowIndex).isEqualTo(0)
        assertThat(repository.playback().position).isEqualTo(0L)
        return@runBlocking
    }

    @Test
    fun playbackWithContents() = runBlocking {
        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(0)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(0L)
        assertThat(repository.playbackWithContents().contents).isEmpty()
        return@runBlocking
    }

    @Test
    fun insertContents() = runBlocking {
        repository.insertContents(PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents().contents).hasSize(1)
        repository.insertContents(PlaybackContent(uri = URI_DUMMY), PlaybackContent(uri = URI_DUMMY))
        assertThat(repository.playbackWithContents().contents).hasSize(3)
        repository.insertContents(URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(4)
        repository.insertContents(URI_DUMMY, URI_DUMMY)
        assertThat(repository.playbackWithContents().contents).hasSize(6)
        return@runBlocking
    }

    @Test
    fun updatePlayback() = runBlocking {
        val playback = repository.playbackWithContents().playback
        playback.windowIndex = 11111
        playback.position = 55555L
        repository.updatePlayback(playback)
        assertThat(repository.playbackWithContents().playback.id).isEqualTo(1L)
        assertThat(repository.playbackWithContents().playback.windowIndex).isEqualTo(11111)
        assertThat(repository.playbackWithContents().playback.position).isEqualTo(55555L)
        assertThat(repository.playbackWithContents().contents).isEmpty()
        return@runBlocking
    }

    @Test
    fun updateAndDeleteContents() = runBlocking {
        repository.insertContents(URI_DUMMY, URI_DUMMY)

        val playbackContent0 = repository.playbackWithContents().contents[0]
        playbackContent0.isPlayed = true
        repository.updateContents(playbackContent0)
        assertThat(repository.playbackWithContents().contents[0].isPlayed).isTrue()
        assertThat(repository.playbackWithContents().contents[1].isPlayed).isFalse()

        repository.deleteContents(playbackContent0)
        assertThat(repository.playbackWithContents().contents[0].isPlayed).isFalse()

        return@runBlocking
    }
}