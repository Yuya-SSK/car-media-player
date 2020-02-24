package com.ssk.car.media.player.device.power

import kotlinx.coroutines.flow.Flow

interface PowerManager {
    suspend fun connectionStateFlow(): Flow<PowerConnectionState>
}