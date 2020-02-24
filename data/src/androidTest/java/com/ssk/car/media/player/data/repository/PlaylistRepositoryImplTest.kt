package com.ssk.car.media.player.data.repository

import android.content.Context
import android.net.Uri
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ssk.car.media.player.data.db.PlaylistRoomDatabase
import com.ssk.car.media.player.data.entity.Playlist
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.assertj.core.api.iterable.Extractor
import org.assertj.core.groups.Tuple
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PlaylistRepositoryImplTest {
    companion object {
        private const val DATA_NAME1: String = "あいうえお"
        private const val DATA_NAME1_MOD: String = "アイウエオ"
        private const val DATA_NAME2: String = "かきくけこ"
        private const val DATA_NAME3: String = "さしすせそ"
        private const val DATA_DESC: String = "説明"
        private val URI_DUMMY: Uri = Uri.parse("content://media/external/images")
    }

    private lateinit var database: PlaylistRoomDatabase
    private lateinit var repository: PlaylistRepositoryImpl

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PlaylistRoomDatabase::class.java).build()
        repository = PlaylistRepositoryImpl(database.playlistDao())
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert1() = runBlocking {
        repository.insert(Playlist(name = DATA_NAME1))
        repository.insert(Playlist(name = DATA_NAME2))
        repository.insert(Playlist(name = DATA_NAME3))
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(1L, DATA_NAME1, "", listOf<Uri>()))
                .contains(tuple(2L, DATA_NAME2, "", listOf<Uri>()))
                .contains(tuple(3L, DATA_NAME3, "", listOf<Uri>()))
        repository.deleteAll()
        repository.insert(Playlist(name = DATA_NAME1), Playlist(name = DATA_NAME2), Playlist(name = DATA_NAME3))
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(4L, DATA_NAME1, "", listOf<Uri>()))
                .contains(tuple(5L, DATA_NAME2, "", listOf<Uri>()))
                .contains(tuple(6L, DATA_NAME3, "", listOf<Uri>()))
        repository.insert(Playlist(4L, DATA_NAME1))
        repository.insert(Playlist(5L, DATA_NAME1))
        repository.insert(Playlist(6L, DATA_NAME1))
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(4L, DATA_NAME1, "", listOf<Uri>()))
                .contains(tuple(5L, DATA_NAME1, "", listOf<Uri>()))
                .contains(tuple(6L, DATA_NAME1, "", listOf<Uri>()))
        return@runBlocking
    }

    @Test
    fun insert2() = runBlocking {
        repository.insert(Playlist(1L, DATA_NAME1, DATA_DESC, arrayListOf(URI_DUMMY)))
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(1L, DATA_NAME1, DATA_DESC, listOf(URI_DUMMY)))
        return@runBlocking
    }

    @Test
    fun update1() = runBlocking {
        repository.insert(Playlist(name = DATA_NAME1))
        repository.update(Playlist(1L, DATA_NAME1_MOD, "説明"))
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(1L, DATA_NAME1_MOD, "説明", listOf<Uri>()))
        return@runBlocking
    }

    @Test
    fun update2() = runBlocking {
        repository.insert(Playlist(1L, DATA_NAME1, DATA_DESC, arrayListOf(URI_DUMMY)))
        var playlist: Playlist
        playlist = repository.playlists()[0]
        playlist.uris.add(URI_DUMMY)
        repository.update(playlist)
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(1L, DATA_NAME1, DATA_DESC, listOf(URI_DUMMY, URI_DUMMY)))
        playlist = repository.playlists()[0]
        playlist.uris.clear()
        repository.update(playlist)
        assertThat(repository.playlists()).extracting(PlaylistExtractor())
                .contains(tuple(1L, DATA_NAME1, DATA_DESC, listOf<Uri>()))
        return@runBlocking
    }

    @Test
    fun delete() = runBlocking {
        repository.insert(Playlist(name = DATA_NAME1), Playlist(name = DATA_NAME2), Playlist(name = DATA_NAME3))
        repository.delete(repository.playlists()[0])
        repository.delete(repository.playlists()[0], repository.playlists()[1])
        assertThat(repository.playlists()).isEmpty()
        return@runBlocking
    }

    private class PlaylistExtractor : Extractor<Playlist, Tuple> {
        override fun extract(playlist: Playlist): Tuple {
            return tuple(playlist.id, playlist.name, playlist.description, playlist.uris)
        }
    }
}