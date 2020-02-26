package com.ysp.camep.service

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import com.ssk.car.media.player.device.power.PowerManager
import com.ssk.car.media.player.log.YLog
import com.ysp.camep.Injection
import com.ysp.camep.R
import com.ysp.camep.notification.NotificationHelper

class MyService : LifecycleService() {
    private val powerManager: PowerManager
    init {
        YLog.methodIn()
        powerManager = Injection.providePowerManager(this@MyService)
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun doOnCreate() {
//                lifecycleScope.launchWhenCreated{
//                    powerManager.connectionStateFlow().collect {
//                        YLog.d("collect")
//                    }
//                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun doOnDestroy() {
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        YLog.methodIn(this.javaClass.simpleName)
        super.onStartCommand(intent, flags, startId)
        val stopAction = NotificationCompat.Action(
            R.mipmap.ic_launcher,
            getString(R.string.notification_monitoring_supply_power_button_text),
            getStopActionPendingIntent(this))
        startForeground(startId,
            NotificationHelper.getMonitoringSupplyPowerNotification(this, stopAction))
        return Service.START_STICKY
    }

//    private fun startActivity() {
//        YLog.methodIn()
//        val startActivityIntent = Intent(applicationContext, MainActivity::class.java)
//        startActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(startActivityIntent)
//    }

    private class StopServiceBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            context.stopService(Intent(context, MyService::class.java))
        }
    }

    fun getStopActionPendingIntent(context: Context): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, StopServiceBroadcastReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT)
    }
}