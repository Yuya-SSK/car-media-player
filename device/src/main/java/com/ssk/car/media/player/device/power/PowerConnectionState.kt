package com.ssk.car.media.player.device.power

import android.content.Intent

enum class PowerConnectionState(private val value: String) {
    Disconnected(Intent.ACTION_POWER_DISCONNECTED),
    Connected(Intent.ACTION_POWER_CONNECTED);
    companion object {
        fun fromValue(value: String): PowerConnectionState {
            for (connectionState in values()) {
                if (value === connectionState.value) {
                    return connectionState
                }
            }
            throw IllegalArgumentException("Invalid parameter [value: $value]")
        }
    }
}