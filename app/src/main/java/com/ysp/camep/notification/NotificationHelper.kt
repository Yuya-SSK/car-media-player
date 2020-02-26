package com.ysp.camep.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.ysp.camep.MainActivity
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
        context: Context, stopAction: NotificationCompat.Action
    ): Notification {
        val channel = Channel.MonitoringSupplyPower
        val pendingIntentStartActivity = TaskStackBuilder.create(context)
            .addNextIntent(Intent(context, MainActivity::class.java))
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        return NotificationCompat.Builder(context, channel.name)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setVisibility(channel.visibility)
            .setContentIntent(pendingIntentStartActivity)
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

//    private fun getPendingIntent(
//        notification: RemoteMessage.Notification,
//        channel: NotificationChannelInfo,
//        data: Map<String, Any>
//    ): PendingIntent {
//        val options = bundleOf(
//            *data.map { it.key to it.value }.toTypedArray()
//        )
//
//        val link = notification.link ?: Uri.parse(data["link"] as? String)
//        if (link != null) {
//            Timber.debug { "getPendingIntent link found" }
//            val intent = Intent(Intent.ACTION_VIEW).setData(link)
//
//            val possibleActivities = packageManager.queryIntentAllActivities(intent)
//            if (possibleActivities.isNotEmpty()) {
//                val droidKaigiResolveInfo =
//                    possibleActivities.firstOrNull { it.activityInfo.packageName == packageName }
//                if (droidKaigiResolveInfo != null) {
//                    Timber.debug { "getPendingIntent link droidkaigi activity found" }
//                    return PendingIntent.getActivity(
//                        this, 0, intent.setPackage(packageName),
//                        PendingIntent.FLAG_UPDATE_CURRENT, options
//                    )
//                }
//                Timber.debug { "getPendingIntent link activity found" }
//                return PendingIntent.getActivity(
//                    this, 0, intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT, options
//                )
//            }
//        }
//        Timber.debug {
//            "getPendingIntent link activity not found. " +
//                    "Choose url by notification channel ${channel.defaultLaunchUrl}"
//        }
//
//        val intent = Intent(Intent.ACTION_VIEW)
//            .setData(Uri.parse(channel.defaultLaunchUrl))
//            .setPackage(application.packageName)
//        return PendingIntent.getActivity(
//            this,
//            0,
//            intent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//    }
}