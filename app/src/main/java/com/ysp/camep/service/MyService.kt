package com.ysp.camep.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.lifecycle.*
import com.ssk.car.media.player.device.power.PowerManager
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.Injection
import com.ysp.camep.MainActivity
import com.ysp.camep.R
import com.ysp.camep.notification.NotificationHelper
import kotlinx.coroutines.flow.collect

class MyService : LifecycleService() {
    private val powerManager: PowerManager = Injection.providePowerManager(this@MyService)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        YLog.methodIn()
        super.onStartCommand(intent, flags, startId)
        startForeground(startId, getMonitoringSupplyPowerNotification(this))
        return Service.START_STICKY
    }

    override fun onCreate() {
        YLog.methodIn()
        super.onCreate()
        lifecycleScope.launchWhenCreated {
            powerManager.connectionStateFlow().collect {
                YLog.d(it.name)
            }
        }
    }

    private fun getMonitoringSupplyPowerNotification(context: Context) : Notification {
        val pendingIntent = TaskStackBuilder.create(context)
            .addNextIntent(Intent(context, MainActivity::class.java))
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)!!
        val stopAction = NotificationCompat.Action(
            R.mipmap.ic_launcher,
            getString(R.string.action_end),
            getStopActionPendingIntent(this))
        return NotificationHelper.getMonitoringSupplyPowerNotification(this, pendingIntent, stopAction)
    }

    private fun getStopActionPendingIntent(context: Context): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, StopServiceBroadcastReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private class StopServiceBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            YLog.methodIn()
            context.stopService(Intent(context, MyService::class.java))
        }
    }
}