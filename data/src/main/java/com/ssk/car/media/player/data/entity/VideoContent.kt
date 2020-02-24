package com.ssk.car.media.player.data.entity

import android.graphics.Bitmap
import android.net.Uri

data class VideoContent(
        val id: Long = 0,
        val uri: Uri = Uri.EMPTY,
        val title: String? = "",
        val thumbnail: Bitmap? = null
)