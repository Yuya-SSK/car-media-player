package com.ysp.camep.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ysp.camep.R

object NotificationHelper {
    fun init(context: Context) {
        val manager = NotificationManagerCompat.from(context)
        manager.createNotificationChannelGroups(
            listOf(createGroup(context, Group.App))
        )
        manager.createNotificationChannels(
            listOf(createChannel(context, Channel.MonitoringSupplyPower))
        )
    }

    fun getMonitoringSupplyPowerNotification(
        context: Context, pendingIntent: PendingIntent, stopAction: NotificationCompat.Action
    ): Notification {
        val channel = Channel.MonitoringSupplyPower
        return NotificationCompat.Builder(context, channel.name)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setVisibility(channel.visibility)
            .setContentIntent(pendingIntent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle(context.getString(R.string.notification_monitoring_supply_power_title))
                    .bigText(context.getString(R.string.notification_monitoring_supply_power_text))
            )
            .addAction(stopAction)
            .build()
    }

    private fun createGroup(context: Context, group: Group): NotificationChannelGroup {
        return NotificationChannelGroup(group.name, context.getString(group.nameResId))
    }

    private fun createChannel(context: Context, channel: Channel): NotificationChannel {
        val ret = NotificationChannel(
            channel.name, context.getString(channel.nameResId), channel.importance
        )
        ret.group = channel.group.name
        return ret
    }

    private enum class Group(@StringRes val nameResId: Int) {
        App(R.string.notification_group_app);
    }

    private enum class Channel(
        @StringRes val nameResId: Int,
        val importance: Int,
        @ColorRes val color: Int,
        val visibility: Int,
        val group: Group
    ) {
        MonitoringSupplyPower(
            R.string.notification_monitoring_supply_power,
            NotificationManagerCompat.IMPORTANCE_LOW,
            Color.BLUE,
            Notification.VISIBILITY_PUBLIC,
            Group.App);
    }
}