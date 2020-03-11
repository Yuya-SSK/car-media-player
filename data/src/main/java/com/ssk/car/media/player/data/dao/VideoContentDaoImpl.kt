package com.ssk.car.media.player.data.dao

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.ssk.car.media.player.data.entity.VideoContent

class VideoContentDaoImpl(context: Context) : VideoContentDao {

    private val contentResolver: ContentResolver = context.contentResolver

    override suspend fun videoContents(): List<VideoContent> {
        val ret = arrayListOf<VideoContent>()
        contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            null, null, null, null)?.use {
            if (!it.moveToFirst()) {
                return@use
            }
            do {
                ret.add(createVideoContent(it))
            } while (it.moveToNext())
        }
        return ret
    }

    override suspend fun videoContent(uri: Uri): VideoContent? {
        contentResolver.query(uri,
            null, null, null, null).use {
            return it?.let { cursor -> createVideoContent(cursor) }
        }
    }

    private fun createVideoContent(cursor: Cursor) : VideoContent {
        val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID))
        val uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
        val title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
        val thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id, MediaStore.Video.Thumbnails.MICRO_KIND, null)
        return VideoContent(id, uri, title, thumbnail)
    }
}