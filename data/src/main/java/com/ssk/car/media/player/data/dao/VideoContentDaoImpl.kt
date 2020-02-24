package com.ssk.car.media.player.data.dao

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.ssk.car.media.player.data.entity.VideoContent

class VideoContentDaoImpl(context: Context) : VideoContentDao {

    private val contentResolver: ContentResolver = context.contentResolver

    override suspend fun videoContents(): List<VideoContent> {
        val videoContents = arrayListOf<VideoContent>()
        contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null)?.use {
            if (!it.moveToFirst()) {
                return@use
            }
            var id: Long
            var uri: Uri
            var title: String?
            var thumbnail: Bitmap?
            do {
                id = it.getLong(it.getColumnIndex(MediaStore.Video.Media._ID))
                uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                title = it.getString(it.getColumnIndex(MediaStore.Video.Media.TITLE))
                thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Video.Thumbnails.MICRO_KIND, null)
                videoContents.add(VideoContent(id, uri, title, thumbnail))
            } while (it.moveToNext())
        }
        return videoContents
    }
}