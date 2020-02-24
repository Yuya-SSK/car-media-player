package com.ssk.car.media.player.device.power

class PowerManagerImpl private constructor(
        private val androidPowerManager: AndroidPowerManager
) : PowerManager {
    companion object {
        @Volatile
        private var instance: PowerManagerImpl? = null
        @JvmStatic
        fun getInstance(powerManager: AndroidPowerManager):PowerManagerImpl =
                instance ?: synchronized(this) {
                    instance ?: PowerManagerImpl(powerManager).also { instance = it }
                }
    }
    override suspend fun connectionStateFlow() = androidPowerManager.connectionStateFlow()
}