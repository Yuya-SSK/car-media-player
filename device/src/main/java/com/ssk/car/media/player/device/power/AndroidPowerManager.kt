package com.ssk.car.media.player.device.power

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.ssk.car.media.player.log.YLog
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidPowerManager(private val context: Context): PowerManager {
    override suspend fun connectionStateFlow(): Flow<PowerConnectionState> = callbackFlow {
        YLog.methodIn("connectionStateFlow")
        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                YLog.methodIn("onReceive : " + intent.action)
                offer(PowerConnectionState.fromValue(intent.action!!))
                YLog.methodOut("onReceive")
            }
        }
        YLog.d("connectionStateFlow : registerReceiver")
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        context.registerReceiver(receiver, intentFilter)
        YLog.d("connectionStateFlow : awaitClose")
        awaitClose {
            YLog.d("connectionStateFlow : awaitClose in")
            context.unregisterReceiver(receiver)
            YLog.d("connectionStateFlow : awaitClose out")
        }
        YLog.methodOut("connectionStateFlow")
    }
}