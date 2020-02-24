package com.ssk.car.media.player.data.repository

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import com.ssk.car.media.player.data.dao.VideoContentDao
import com.ssk.car.media.player.data.dao.VideoContentDaoImpl
import com.ssk.car.media.player.data.entity.VideoContent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class VideoContentRepositoryImplTest {

    @Rule @JvmField
    var grantPermissionRule: GrantPermissionRule =
            GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE)

    private val mockVideoContentDao = mockk<VideoContentDao>()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun videoContentList() = runBlocking {
        val repository = VideoContentRepositoryImpl(VideoContentDaoImpl(ApplicationProvider.getApplicationContext<Context>()))
        Log.d("VideoContentRepositoryTest", repository.videoContentList().toString())
        return@runBlocking
    }

    @Test
    fun videoContentListMock() = runBlocking {
        val repository = VideoContentRepositoryImpl(mockVideoContentDao)

        coEvery { mockVideoContentDao.videoContents() } returns listOf()
        assertThat(repository.videoContentList()).isEmpty()
        coVerify { repository.videoContentList() }

        coEvery { mockVideoContentDao.videoContents() } returns listOf(VideoContent(), VideoContent())
        assertThat(repository.videoContentList()).hasSize(2)
        coVerify { repository.videoContentList() }

        confirmVerified(mockVideoContentDao)
        return@runBlocking
    }
}