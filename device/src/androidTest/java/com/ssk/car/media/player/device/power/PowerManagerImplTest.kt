package com.ssk.car.media.player.device.power

import androidx.test.platform.app.InstrumentationRegistry
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.junit.Before
import org.junit.Test

class PowerManagerImplTest {

    private lateinit var powerManager: PowerManager

    @Before
    fun setUp() {
        powerManager = PowerManagerImpl.getInstance(
                AndroidPowerManager(InstrumentationRegistry.getInstrumentation().targetContext))
    }

    @Test
    fun connectionStateFlow() {
        YLog.d("connectionStateFlow in")
        runBlocking {
            YLog.d("connectionStateFlow runBlocking in")
            val job = launch(Dispatchers.Default) {
                val flow = powerManager.connectionStateFlow()
                flow.collect {
                    YLog.d("受信者1 $it")
                }
                flow.collect {
                    YLog.d("受信者2 $it")
                }
            }
            delay(1000 * 5)
            job.cancelAndJoin()
            YLog.d("connectionStateFlow runBlocking out")
        }
        YLog.d("connectionStateFlow out")
    }
}