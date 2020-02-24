package com.ssk.car.media.player.device.power

import androidx.test.platform.app.InstrumentationRegistry
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.broadcastIn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import org.junit.Before
import org.junit.Test

class AndroidPowerManagerTest {

    private lateinit var powerManager: AndroidPowerManager

    @Before
    fun setUp() {
        powerManager = AndroidPowerManager(
                InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun connectionStateFlow() = runBlocking {
        YLog.d("connectionStateFlow runBlocking in")
        val job1 = launch(Dispatchers.Default) {
            YLog.d("[job1]connectionStateFlow launch in")
            powerManager.connectionStateFlow().collect { YLog.d("$it") }
            YLog.d("[job1]connectionStateFlow launch out")
        }
        job1.cancelAndJoin()
        delay(1000 * 2)
        YLog.d("connectionStateFlow runBlocking out")
        delay(1000 * 1)
    }

    @Test
    fun connectionStateBroadcast() = runBlocking {
        YLog.d("connectionStateFlow runBlocking in")
        val flow = powerManager.connectionStateFlow()
                .flowOn(newSingleThreadContext("Sender")).broadcastIn(GlobalScope).asFlow()
        val job1 = launch(Dispatchers.Default) {
            YLog.d("[job1]connectionStateFlow launch in")
            flow.collect { YLog.d("$it") }
            YLog.d("[job1]connectionStateFlow launch out")
        }
        val job2 = launch(Dispatchers.Default) {
            YLog.d("[job2]connectionStateFlow launch in")
            flow.collect { YLog.d("$it") }
            YLog.d("[job2]connectionStateFlow launch out")
        }
        delay(1000 * 2)
        job1.cancelAndJoin()
        delay(1000 * 2)
        job2.cancelAndJoin()
        YLog.d("connectionStateFlow runBlocking out")
        delay(1000 * 1)
    }
}